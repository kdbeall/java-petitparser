package org.petitparser.parser;

import org.petitparser.context.Context;
import org.petitparser.context.Result;

/**
 * A parser that answers a flat copy of the range my delegate parses.
 * 
 * @author Lukas Renggli (renggli@gmail.com)
 */
public class FlattenParser extends Parser<String> {

  private final Parser<?> delegate;

  public FlattenParser(Parser<?> delegate) {
    this.delegate = delegate;
  }

  @Override
  public Result<String> parse(Context context) {
    Result<?> result = delegate.parse(context);
    if (result.isSuccess()) {
      String flattened = context.getBuffer().subSequence(context.getPosition(),
          result.getPosition());
      return result.success(flattened);
    } else {
      return result.cast();
    }
  }
}
