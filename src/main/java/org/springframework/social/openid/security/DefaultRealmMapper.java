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

import java.util.Collections;
import java.util.Map;

public class DefaultRealmMapper implements IRealmMapper {

	private Map<String, String> realmMapping = Collections.emptyMap();
	
	@Override
	public String getMapping(String returnToUrl) {
		return realmMapping.get(returnToUrl);
	}

	/**
	 * Maps the <tt>return_to url</tt> to a realm, for example: <pre>
	 * http://www.example.com/j_spring_openid_security_check ->
	 * http://www.example.com/realm</tt> </pre> If no mapping is provided then
	 * the returnToUrl will be parsed to extract the protocol, hostname and port
	 * followed by a trailing slash. This means that
	 * <tt>http://www.example.com/j_spring_openid_security_check</tt> will
	 * automatically become <tt>http://www.example.com:80/</tt>
	 * 
	 * @param realmMapping
	 *            containing returnToUrl -> realm mappings
	 */
	public void setRealmMapping(Map<String, String> realmMapping) {
		this.realmMapping = realmMapping;
	}
}
