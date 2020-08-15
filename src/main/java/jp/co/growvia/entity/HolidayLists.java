package jp.co.growvia.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class HolidayLists {

    private List<Holiday> holiday;

    public HolidayLists() {
    	holiday = new ArrayList<>();
    }
}
