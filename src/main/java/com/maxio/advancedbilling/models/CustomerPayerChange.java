/*
 * AdvancedBilling
 *
 * This file was automatically generated for Maxio by APIMATIC v3.0 ( https://www.apimatic.io ).
 */

package com.maxio.advancedbilling.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This is a model class for CustomerPayerChange type.
 */
public class CustomerPayerChange {
    private Object before;
    private Object after;

    /**
     * Default constructor.
     */
    public CustomerPayerChange() {
    }

    /**
     * Initialization constructor.
     * @param  before  Object value for before.
     * @param  after  Object value for after.
     */
    public CustomerPayerChange(
            Object before,
            Object after) {
        this.before = before;
        this.after = after;
    }

    /**
     * Getter for Before.
     * @return Returns the Object
     */
    @JsonGetter("before")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getBefore() {
        return before;
    }

    /**
     * Setter for Before.
     * @param before Value for Object
     */
    @JsonSetter("before")
    public void setBefore(Object before) {
        this.before = before;
    }

    /**
     * Getter for After.
     * @return Returns the Object
     */
    @JsonGetter("after")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public Object getAfter() {
        return after;
    }

    /**
     * Setter for After.
     * @param after Value for Object
     */
    @JsonSetter("after")
    public void setAfter(Object after) {
        this.after = after;
    }

    /**
     * Converts this CustomerPayerChange into string format.
     * @return String representation of this class
     */
    @Override
    public String toString() {
        return "CustomerPayerChange [" + "before=" + before + ", after=" + after + "]";
    }

    /**
     * Builds a new {@link CustomerPayerChange.Builder} object.
     * Creates the instance with the state of the current model.
     * @return a new {@link CustomerPayerChange.Builder} object
     */
    public Builder toBuilder() {
        Builder builder = new Builder()
                .before(getBefore())
                .after(getAfter());
        return builder;
    }

    /**
     * Class to build instances of {@link CustomerPayerChange}.
     */
    public static class Builder {
        private Object before;
        private Object after;



        /**
         * Setter for before.
         * @param  before  Object value for before.
         * @return Builder
         */
        public Builder before(Object before) {
            this.before = before;
            return this;
        }

        /**
         * Setter for after.
         * @param  after  Object value for after.
         * @return Builder
         */
        public Builder after(Object after) {
            this.after = after;
            return this;
        }

        /**
         * Builds a new {@link CustomerPayerChange} object using the set fields.
         * @return {@link CustomerPayerChange}
         */
        public CustomerPayerChange build() {
            return new CustomerPayerChange(before, after);
        }
    }
}
