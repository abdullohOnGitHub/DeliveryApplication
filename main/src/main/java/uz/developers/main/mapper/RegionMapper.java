package uz.developers.main.mapper;

import uz.developers.main.dto.RegionDto;
import uz.developers.main.entity.Region;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class RegionMapper {

    public static Region toEntity(RegionDto regionDto) {
        if (regionDto != null) return new Region(regionDto.getId(), regionDto.getName());
        return new Region();
    }

    public static List<Region> toEntity(List<RegionDto> regionDto) {
        List<Region> regions = null;
        if (regionDto != null) {
            regions = new LinkedList<>();
            for (RegionDto region : regionDto) {
                regions.add(toEntity(region));
            }
        }
        return regions;
    }

    public static RegionDto toDto(Region region) {
        if (region != null) return new RegionDto(region.getId(), region.getName());
        return new RegionDto();
    }

    public static Set<RegionDto> toDto(Set<Region> regions) {
        HashSet<RegionDto> regionDtos = null;
        if (regions != null) {
            regionDtos = new HashSet<>();
            for (Region region : regions) {
                regionDtos.add(toDto(region));
            }
        }
        return regionDtos;
    }
}
