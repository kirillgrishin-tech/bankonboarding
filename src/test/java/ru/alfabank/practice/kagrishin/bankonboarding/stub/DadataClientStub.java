package ru.alfabank.practice.kagrishin.bankonboarding.stub;

import wiremock.org.apache.hc.core5.http.ContentType;
import wiremock.org.eclipse.jetty.http.HttpHeader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class DadataClientStub {

    private static final String DADATA_PATH = "/rs/suggest/address";

    public static void returnValidAddressWithNineFialLevel() {
        stubFor(post(urlPathMatching(DADATA_PATH))
                .willReturn(ok()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), ContentType.APPLICATION_JSON.getMimeType())
                        .withBodyFile("one-address-response.json")));
    }

    public static void returnEmptyResponse() {
        stubFor(post(urlPathMatching(DADATA_PATH))
                .willReturn(ok()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), ContentType.APPLICATION_JSON.getMimeType())));
    }

    public static void returnUnauthorisedErrorResponse() {
        stubFor(post(urlPathMatching(DADATA_PATH))
                .willReturn(unauthorized()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), ContentType.APPLICATION_JSON.getMimeType())));
    }

    public static void returnAddressesWithoutNineFiasLevel() {
        stubFor(post(urlPathMatching(DADATA_PATH))
                .willReturn(ok()
                        .withHeader(HttpHeader.CONTENT_TYPE.asString(), ContentType.APPLICATION_JSON.getMimeType())
                        .withBodyFile("addresses-no-nine-fias-level.json")));
    }
}
