package com.dfwcomputech.cpoint.common.util;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MockDtoBean {
	private String strAttribute;
	private Integer intAttribute;
	private BigDecimal decAttribute;
}
