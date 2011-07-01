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

import java.net.MalformedURLException;
import java.net.URL;

public class WildcardRealmMapper implements IRealmMapper {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(WildcardRealmMapper.class);
	
	@Override
	public String getMapping(String returnToUrl) {
		try {
			URL url = new URL(returnToUrl);
			
			StringBuilder buf = new StringBuilder();
			buf.append(url.getProtocol()).append("://");
			
			String[] hostParts = url.getHost().split("\\.");
			if (hostParts.length <= 2) {
				buf.append(url.getHost());
			} else {
				buf.append("*.").append(hostParts[hostParts.length - 2]).append(".").append(hostParts[hostParts.length - 1]);
			}
			
			if (url.getPort() != -1) {
				buf.append(":").append(url.getPort());
			}
			
			buf.append("/");
			
			return buf.toString();
		} catch (MalformedURLException e) {
			log.warn("returnToUrl not a valid URL", e);
			return null;
		}
	}

}
