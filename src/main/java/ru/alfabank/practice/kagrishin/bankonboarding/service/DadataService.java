package ru.alfabank.practice.kagrishin.bankonboarding.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.alfabank.practice.kagrishin.bankonboarding.client.DadataClient;
import ru.alfabank.practice.kagrishin.bankonboarding.model.Address;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata.AddressRequest;
import ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata.AddressResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class DadataService {

    private final DadataClient dadataClient;

    public List<Address> getAddresses(String query) {
        List<Address> addresses = new ArrayList<>();
        Optional<AddressResponse> response = dadataClient.getAddresses(new AddressRequest(query));
        response.flatMap(resp -> Optional.ofNullable(resp.suggestions()))
                .ifPresent(suggestions -> suggestions
                        .forEach(suggestion -> Optional.ofNullable(suggestion.data())
                                .ifPresent(data -> addresses.add(
                                                Address.builder().fiasLevel(data.fiasLevel()).build()
                                        )
                                )
                        ));
        return addresses;
    }
}
