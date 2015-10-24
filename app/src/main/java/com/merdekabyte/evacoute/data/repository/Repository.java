package com.merdekabyte.evacoute.data.repository;

import com.parse.ParseException;

import java.util.List;

interface Repository<T> {

    public List<T> resolveAll() throws ParseException;

    public T resolveById(String objectId) throws ParseException;

}
