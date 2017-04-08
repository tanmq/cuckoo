ALTER TABLE `user`
ADD COLUMN `area` VARCHAR(64) NULL COMMENT '所在地区' AFTER `uts`,
ADD COLUMN `coverUrl` VARCHAR(128) NULL COMMENT '个人封面大图' AFTER `area`,
ADD COLUMN `signature` VARCHAR(128) NULL COMMENT '个性签名' AFTER `coverUrl`,
ADD COLUMN `avatarUrlOrigin` VARCHAR(128) NULL COMMENT '头像原图' AFTER `avatarUrl`;




-- 2017-04-08 13:30
ALTER TABLE `user`
ADD COLUMN `followCount` INT NULL DEFAULT 0 COMMENT '关注者人数' AFTER `signature`,
ADD COLUMN `followedCount` INT NULL DEFAULT 0 COMMENT '被关注人数' AFTER `followCount`;
