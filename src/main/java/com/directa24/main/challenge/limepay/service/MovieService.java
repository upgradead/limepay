package com.directa24.main.challenge.limepay.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MovieService {
    List<String> getDirectors(final int threshold);
}
