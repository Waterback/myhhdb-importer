package com.wb.myhhdb.myhhdbimporter.entity;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingCSVEntityRepository extends CrudRepository<BookingCSVEntry, Long> {

    List<BookingCSVEntry> findByHashValue(String hash);

}
