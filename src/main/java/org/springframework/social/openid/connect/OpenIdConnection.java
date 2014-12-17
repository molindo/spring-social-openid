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

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.AbstractConnection;
import org.springframework.social.openid.api.OpenId;

public class OpenIdConnection extends AbstractConnection<OpenId> {

	private static final long serialVersionUID = 1L;

	private final OpenIdServiceProvider serviceProvider;
	private final String verifiedOpenId;
	private transient OpenId api;

	public OpenIdConnection(String verifiedOpenId, OpenIdServiceProvider serviceProvider, ApiAdapter<OpenId> apiAdapter) {
		super(apiAdapter);
		this.serviceProvider = serviceProvider;
		this.verifiedOpenId = verifiedOpenId;
		this.api = initApi();
	}

	public OpenIdConnection(ConnectionData data, OpenIdServiceProvider serviceProvider, ApiAdapter<OpenId> apiAdapter) {
		super(data, apiAdapter);
		this.serviceProvider = serviceProvider;
		this.verifiedOpenId = data.getProviderUserId();
		this.api = initApi();
	}

	private OpenId initApi() {
		return serviceProvider.getApi(verifiedOpenId);
	}
	
	@Override
	public OpenId getApi() {
		synchronized (getMonitor()) {
			return api;
		}
	}

	@Override
	public ConnectionData createData() {
		synchronized (getMonitor()) {
			return new ConnectionData(getKey().getProviderId(), getKey().getProviderUserId(), getDisplayName(), getProfileUrl(), getImageUrl(), null, null, null, null);
		}
	}

	@Override
	public void refresh() {
		api = initApi();
	}

}
