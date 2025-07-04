package ru.alfabank.practice.kagrishin.bankonboarding.model.dto.dadata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Data(

        @JsonProperty("postal_code")
        String postalCode,
        @JsonProperty("country")
        String country,
        @JsonProperty("country_iso_code")
        String countryIsoCode,
        @JsonProperty("federal_district")
        String federalDistrict,
        @JsonProperty("region_fias_id")
        String regionFiasId,
        @JsonProperty("region_kladr_id")
        String regionKladrId,
        @JsonProperty("region_iso_code")
        String regionIsoCode,
        @JsonProperty("region_with_type")
        String regionWithType,
        @JsonProperty("region_type")
        String regionType,
        @JsonProperty("region_type_full")
        String regionTypeFull,
        @JsonProperty("region")
        String region,
        @JsonProperty("area_fias_id")
        String areaFiasId,
        @JsonProperty("area_kladr_id")
        String areaKladrId,
        @JsonProperty("area_with_type")
        String areaWithType,
        @JsonProperty("area_type")
        String areaType,
        @JsonProperty("area_type_full")
        String areaTypeFull,
        @JsonProperty("area")
        String area,
        @JsonProperty("city_fias_id")
        String cityFiasId,
        @JsonProperty("city_kladr_id")
        String cityKladrId,
        @JsonProperty("city_with_type")
        String cityWithType,
        @JsonProperty("city_type")
        String cityType,
        @JsonProperty("city_type_full")
        String cityTypeFull,
        @JsonProperty("city")
        String city,
        @JsonProperty("city_area")
        String cityArea,
        @JsonProperty("city_district_fias_id")
        String cityDistrictFiasId,
        @JsonProperty("city_district_kladr_id")
        String cityDistrictKladrId,
        @JsonProperty("city_district_with_type")
        String cityDistrictWithType,
        @JsonProperty("city_district_type")
        String cityDistrictType,
        @JsonProperty("city_district_type_full")
        String cityDistrictTypeFull,
        @JsonProperty("city_district")
        String cityDistrict,
        @JsonProperty("settlement_fias_id")
        String settlementFiasId,
        @JsonProperty("settlement_kladr_id")
        String settlementKladrId,
        @JsonProperty("settlement_with_type")
        String settlementWithType,
        @JsonProperty("settlement_type")
        String settlementType,
        @JsonProperty("settlement_type_full")
        String settlementTypeFull,
        @JsonProperty("settlement")
        String settlement,
        @JsonProperty("street_fias_id")
        String streetFiasId,
        @JsonProperty("street_kladr_id")
        String streetKladrId,
        @JsonProperty("street_with_type")
        String streetWithType,
        @JsonProperty("street_type")
        String streetType,
        @JsonProperty("street_type_full")
        String streetTypeFull,
        @JsonProperty("street")
        String street,
        @JsonProperty("stead_fias_id")
        String steadFiasId,
        @JsonProperty("stead_cadnum")
        String steadCadnum,
        @JsonProperty("stead_type")
        String steadType,
        @JsonProperty("stead_type_full")
        String steadTypeFull,
        @JsonProperty("stead")
        String stead,
        @JsonProperty("house_fias_id")
        String houseFiasId,
        @JsonProperty("house_kladr_id")
        String houseKladrId,
        @JsonProperty("house_cadnum")
        String houseCadnum,
        @JsonProperty("house_flat_count")
        String houseFlatCount,
        @JsonProperty("house_type")
        String houseType,
        @JsonProperty("house_type_full")
        String houseTypeFull,
        @JsonProperty("house")
        String house,
        @JsonProperty("block_type")
        String blockType,
        @JsonProperty("block_type_full")
        String blockTypeFull,
        @JsonProperty("block")
        String block,
        @JsonProperty("entrance")
        String entrance,
        @JsonProperty("floor")
        String floor,
        @JsonProperty("flat_fias_id")
        String flatFiasId,
        @JsonProperty("flat_cadnum")
        String flatCadnum,
        @JsonProperty("flat_type")
        String flatType,
        @JsonProperty("flat_type_full")
        String flatTypeFull,
        @JsonProperty("flat")
        String flat,
        @JsonProperty("flat_area")
        String flatArea,
        @JsonProperty("square_meter_price")
        String squareMeterPrice,
        @JsonProperty("flat_price")
        String flatPrice,
        @JsonProperty("postal_box")
        String postalBox,
        @JsonProperty("fias_id")
        String fiasId,
        @JsonProperty("fias_code")
        String fiasCode,
        @JsonProperty("fias_level")
        String fiasLevel,
        @JsonProperty("fias_actuality_state")
        String fiasActualityState,
        @JsonProperty("kladr_id")
        String kladrId,
        @JsonProperty("geoname_id")
        String geonameId,
        @JsonProperty("capital_marker")
        String capitalMarker,
        @JsonProperty("okato")
        String okato,
        @JsonProperty("oktmo")
        String oktmo,
        @JsonProperty("tax_office")
        String taxOffice,
        @JsonProperty("tax_office_legal")
        String taxOfficeLegal,
        @JsonProperty("timezone")
        String timezone,
        @JsonProperty("geo_lat")
        String geoLat,
        @JsonProperty("geo_lon")
        String geoLon,
        @JsonProperty("beltway_hit")
        String beltwayHit,
        @JsonProperty("beltway_distance")
        String beltwayDistance,
        @JsonProperty("metro")
        String metro,
        @JsonProperty("divisions")
        String divisions,
        @JsonProperty("qc_geo")
        String qcGeo,
        @JsonProperty("qc_complete")
        String qcComplete,
        @JsonProperty("qc_house")
        String qcHouse,
        @JsonProperty("history_values")
        List<String> historyValues,
        @JsonProperty("unparsed_parts")
        String unparsedParts,
        @JsonProperty("source")
        String source,
        @JsonProperty("qc")
        String qc
) {
}
