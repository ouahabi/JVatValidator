/*******************************************************************************
 * Copyright 2015 Eugen Covaci
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ro.go.kpaxplanet.vat.validator.impl;


/**
 * Finnish VAT number validator.
 * 
 * @author eugen covaci
 * 
 */
public class FI extends AbstractVatFormalValidator  {

	@Override
	public boolean validateDigits(String vatNumber) {
		
		// Checks the check digits of a Finnish VAT number.

		int total = 0;
		int[] multipliers = { 7, 9, 10, 5, 8, 4, 2 };

		// Extract the next digit and multiply by the counter.
		for (int i = 0; i < 7; i++)
			total += Integer.parseInt(vatNumber.substring(i, i + 1)) * multipliers[i];

		// Establish check digit.
		total = 11 - total % 11;
		if (total > 9) {
			total = 0;
		}

		// Compare it with the last character of the VAT number. If it's the
		// same, then it's valid.
		return total == Integer.parseInt(vatNumber.substring(7, 8));
	}

	@Override
	public String[] getRegexArray() {
		return new String[]{"^(\\d{8})$"};
	}

}
