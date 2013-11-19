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
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.openid.api.OpenId;

public class OpenIdAdapter implements ApiAdapter<OpenId> {

	@Override
	public boolean test(OpenId api) {
		return true;
	}

	@Override
	public void setConnectionValues(OpenId api, ConnectionValues values) {
		values.setProviderUserId(api.getVerifiedOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(OpenId api) {
		return new UserProfileBuilder().setUsername(api.getVerifiedOpenId()).build();
	}

	
	@Override
	public void updateStatus(OpenId api, String message) {
	}

}
