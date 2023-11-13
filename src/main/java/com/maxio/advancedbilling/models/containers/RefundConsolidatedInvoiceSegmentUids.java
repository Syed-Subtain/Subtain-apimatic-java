/*
 * AdvancedBilling
 *
 * This file was automatically generated for Maxio by APIMATIC v3.0 ( https://www.apimatic.io ).
 */

package com.maxio.advancedbilling.models.containers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maxio.advancedbilling.ApiHelper;
import io.apimatic.core.annotations.TypeCombinator.TypeCombinatorCase;
import io.apimatic.core.annotations.TypeCombinator.TypeCombinatorStringCase;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This is a container class for one-of types.
 */
@JsonDeserialize(using = RefundConsolidatedInvoiceSegmentUids.RefundConsolidatedInvoiceSegmentUidsDeserializer.class)
public abstract class RefundConsolidatedInvoiceSegmentUids {
    
    /**
     * This is List of String case.
     * @param listOfString List of String value for listOfString.
     * @return The ListOfStringCase object.
     */
    public static RefundConsolidatedInvoiceSegmentUids fromListOfString(List<String> listOfString) {
        return listOfString == null ? null : new ListOfStringCase(listOfString);
    }

    /**
     * This is String case.
     * @param mString String value for mString.
     * @return The MStringCase object.
     */
    public static RefundConsolidatedInvoiceSegmentUids fromMString(String mString) {
        return mString == null ? null : new MStringCase(mString);
    }

    /**
     * Method to match from the provided one-of cases.
     * @param <R> The type to return after applying callback.
     * @param cases The one-of type cases callback.
     * @return The one-of matched case.
     */
    public abstract <R> R match(Cases<R> cases);

    /**
     * This is interface for one-of cases.
     * @param <R> The type to return after applying callback.
     */
    public interface Cases<R> {
        R listOfString(List<String> listOfString);

        R mString(String mString);
    }

    /**
     * This is a implementation class for ListOfStringCase.
     */
    @JsonDeserialize(using = JsonDeserializer.None.class)
    @TypeCombinatorCase(type = "List<String>")
    private static class ListOfStringCase extends RefundConsolidatedInvoiceSegmentUids {

        @JsonValue
        private List<String> listOfString;

        ListOfStringCase(List<String> listOfString) {
            this.listOfString = listOfString;
        }

        @Override
        public <R> R match(Cases<R> cases) {
            return cases.listOfString(this.listOfString);
        }

        @JsonCreator
        private ListOfStringCase(JsonNode jsonNode) throws IOException {
            this.listOfString = ApiHelper.deserializeArray(jsonNode,
                String[].class);
        }

        @Override
        public String toString() {
            return listOfString.toString();
        }
    }

    /**
     * This is a implementation class for MStringCase.
     */
    @JsonDeserialize(using = JsonDeserializer.None.class)
    @TypeCombinatorStringCase
    @TypeCombinatorCase(type = "String")
    private static class MStringCase extends RefundConsolidatedInvoiceSegmentUids {

        @JsonValue
        private String mString;

        MStringCase(String mString) {
            this.mString = mString;
        }

        @Override
        public <R> R match(Cases<R> cases) {
            return cases.mString(this.mString);
        }

        @JsonCreator
        private MStringCase(JsonNode jsonNode) throws IOException {
            if (jsonNode.isTextual()) {
                this.mString = ApiHelper.deserialize(jsonNode, String.class);
            } else {
                throw new IllegalArgumentException();
            }
        }

        @Override
        public String toString() {
            return mString.toString();
        }
    }

    /**
     * This is a custom deserializer class for RefundConsolidatedInvoiceSegmentUids.
     */
    protected static class RefundConsolidatedInvoiceSegmentUidsDeserializer
            extends JsonDeserializer<RefundConsolidatedInvoiceSegmentUids> {

        @Override
        public RefundConsolidatedInvoiceSegmentUids deserialize(JsonParser jp, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            ObjectCodec oc = jp.getCodec();
            JsonNode node = oc.readTree(jp);
            return ApiHelper.deserialize(node, Arrays.asList(ListOfStringCase.class,
                    MStringCase.class), true);
        }
    }

}