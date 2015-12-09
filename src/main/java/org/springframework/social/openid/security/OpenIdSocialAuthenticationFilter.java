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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SocialAuthenticationServiceLocator;

/**
 * overrides {@link SocialAuthenticationFilter#detectRejection(HttpServletRequest)} to support
 * OpenID requests.
 *
 */
public class OpenIdSocialAuthenticationFilter extends SocialAuthenticationFilter {

	private static final List<String> EXPECTED_OPENID_PARAMS = Collections.unmodifiableList(Arrays.asList(
			"openid_identifier", "openid.identity"));

	public OpenIdSocialAuthenticationFilter(final AuthenticationManager authManager, final UserIdSource userIdSource, final UsersConnectionRepository usersConnectionRepository, final SocialAuthenticationServiceLocator authServiceLocator) {
		super(authManager, userIdSource, usersConnectionRepository, authServiceLocator);
	}

	@Override
	protected boolean detectRejection(final HttpServletRequest request) {
		if (!super.detectRejection(request)) {
			return false;
		} else {
			return isOpenIdRequest(request);
		}
	}

	public static boolean isOpenIdRequest(final HttpServletRequest request) {
		final Set<?> parameterKeys = request.getParameterMap().keySet();
		if (!parameterKeys.isEmpty()) {
			for (final String expected : EXPECTED_OPENID_PARAMS) {
				if (parameterKeys.contains(expected)) {
					return false;
				}
			}
		}
		return true;
	}

}
