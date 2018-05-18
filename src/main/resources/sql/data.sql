INSERT INTO public.users(users_id, users_username, users_password, users_salt, users_role)
	VALUES ('d1098e96-ca3d-11e7-abc4-cec278b6b50a', 'matthewpoletin', '4E7BF6C7FD2FA65D35731D1F212BF55A834A1F51', 'LtIS4YMQ0b', 1);

INSERT INTO public.users(users_id, users_username, users_password, users_salt, users_role)
	VALUES ('210e8d5b-8a1b-4b6e-93f4-0af7be69e38f', 'kormvina', '4E7BF6C7FD2FA65D35731D1F212BF55A834A1F51', 'LtIS4YMQ0b', 3);

INSERT INTO public.twitch_token(twitch_token_id, twitch_token_access_token, twitch_token_refresh_token, twitch_token_expires_in, twitch_token_scope, twitch_token_user_id)
	VALUES (1, 'a5hcxmb5s35e6c3nvijlx6xfk7lr5n', 'nnyzp4shhh31e39qlllta26ixtvyz4944a5akmiv2usx156rb3', 13809, 'user_read user_subscriptions viewing_activity_read', 'd1098e96-ca3d-11e7-abc4-cec278b6b50a')
