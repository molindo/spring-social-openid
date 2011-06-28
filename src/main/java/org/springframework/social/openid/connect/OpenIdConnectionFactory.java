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

package org.springframework.social.openid.connect;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.openid.api.OpenId;

public class OpenIdConnectionFactory extends ConnectionFactory<OpenId> {

	public OpenIdConnectionFactory() {
		super("openid", new OpenIdServiceProvider(), new OpenIdAdapter());
	}

	@Override
	public Connection<OpenId> createConnection(ConnectionData data) {
		return new OpenIdConnection(data, (OpenIdServiceProvider) getServiceProvider(), getApiAdapter());
	}

}
