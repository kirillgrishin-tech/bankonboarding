package ru.alfabank.practice.kagrishin.bankonboarding.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alfabank.practice.kagrishin.bankonboarding.config.DadataClientConfiguration;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata.AddressRequest;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata.AddressResponse;

import java.util.Optional;

@FeignClient(name = "dadataClient", configuration = DadataClientConfiguration.class)
public interface DadataClient {

    @PostMapping(value = "/rs/suggest/address",
            headers = {
                    "Content-Type=application/json",
                    "Accept=application/json",
            })
    Optional<AddressResponse> getAddresses(AddressRequest request);
}
