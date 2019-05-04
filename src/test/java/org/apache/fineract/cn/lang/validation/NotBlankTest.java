/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.fineract.cn.lang.validation;

import org.apache.fineract.cn.lang.validation.constraints.NotBlank;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * @author Ebenezer Graham
 */
public class NotBlankTest {
  @Test
  public void validStringNotNullNotWhitespacesOnly()
  {
    final AnnotatedField annotatedField = new AnnotatedField("Correct String");
    Assert.assertTrue(isValid(annotatedField));
  }

  @Test
  public void invalidWhitespaceOnly()
  {
    final AnnotatedField annotatedField = new AnnotatedField("   ");
    assertFalse(isValid(annotatedField));
  }

  @Test
  public void invalidNullString()
  {
    final AnnotatedField annotatedField = new AnnotatedField(null);
    assertFalse(isValid(annotatedField));
  }

  private boolean isValid(final AnnotatedField annotatedField)
  {

    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<AnnotatedField>> errors = validator.validate(annotatedField);

    return errors.size() == 0;
  }

  private static class AnnotatedField {
    
    @NotBlank
    String string;
    
    AnnotatedField(final String string) {
      this.string = string;
    }
  }
}
