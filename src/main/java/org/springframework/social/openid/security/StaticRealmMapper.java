/**
 * Copyright 2010 Molindo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.social.openid.security;

public class StaticRealmMapper implements IRealmMapper {

	private String _mapping;

	@Override
	public String getMapping(String returnToUrl) {
		return getMapping();
	}

	public String getMapping() {
		return _mapping;
	}

	public void setMapping(String mapping) {
		_mapping = mapping;
	}

}
