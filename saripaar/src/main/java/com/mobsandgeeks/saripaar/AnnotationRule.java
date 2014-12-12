/*
 * Copyright (C) 2014 Mobs and Geeks
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mobsandgeeks.saripaar;

import android.content.Context;

import java.lang.annotation.Annotation;

/**
 * These are implementations that are tied to rule annotations. All stock rule annotations have an
 * {@link com.mobsandgeeks.saripaar.AnnotationRule} implementation paired to them, using the
 * {@link com.mobsandgeeks.saripaar.annotation.ValidateUsing} annotation. Like stock annotations,
 * custom annotations must have a corresponding {@link com.mobsandgeeks.saripaar.AnnotationRule}
 * as well.
 *
 * @author Ragunath Jawahar {@literal <rj@mobsandgeeks.com>}
 * @since 2.0
 */
public abstract class AnnotationRule<RULE_ANNOTATION extends Annotation, DATA_TYPE>
        implements Rule {

    protected final RULE_ANNOTATION mRuleAnnotation;

    /**
     * Constructor.
     *
     * @param ruleAnnotation  The rule {@link java.lang.annotation.Annotation} to which this
     *      rule is paired.
     */
    protected AnnotationRule(final RULE_ANNOTATION ruleAnnotation) {
        if (ruleAnnotation == null) {
            throw new IllegalArgumentException("'ruleAnnotation' cannot be null.");
        }
        mRuleAnnotation = ruleAnnotation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(final Context context) {
        final int messageResId = Reflector.getAttributeValue(mRuleAnnotation, "messageResId",
            Integer.class);

        return messageResId != -1
            ? context.getString(messageResId)
            : Reflector.getAttributeValue(mRuleAnnotation, "message", String.class);
    }

    /**
     * Checks if the data received from the adapter adheres to this rule.
     *
     * @param data  Data from a {@link com.mobsandgeeks.saripaar.adapter.ViewDataAdapter}.
     *
     * @return true if valid, false otherwise.
     */
    public abstract boolean isValid(DATA_TYPE data);

}
