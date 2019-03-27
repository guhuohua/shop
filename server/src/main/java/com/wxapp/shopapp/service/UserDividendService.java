package com.wxapp.shopapp.service;

import java.util.List;
import java.util.Map;

public interface UserDividendService {
    List<Map<String, Object>> findAllUserDividend() throws Exception;
}
