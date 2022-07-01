-- 장소
ALTER TABLE `PLACE`
	DROP PRIMARY KEY; -- 장소 기본키

-- 사용자
ALTER TABLE `USER`
	DROP PRIMARY KEY; -- 사용자 기본키

-- 리뷰
ALTER TABLE `REVIEW`
	DROP PRIMARY KEY; -- 리뷰 기본키

-- 첨부파일
ALTER TABLE `ADD_FILE`
	DROP PRIMARY KEY; -- 첨부파일 기본키

-- 포인트
ALTER TABLE `POINT`
	DROP PRIMARY KEY; -- 포인트 기본키


-- 리뷰 유니크 인덱스
DROP INDEX `UIX_REVIEW` ON `REVIEW`;

-- 장소
DROP TABLE IF EXISTS `PLACE` RESTRICT;

-- 사용자
DROP TABLE IF EXISTS `USER` RESTRICT;

-- 리뷰
DROP TABLE IF EXISTS `REVIEW` RESTRICT;

-- 첨부파일
DROP TABLE IF EXISTS `ADD_FILE` RESTRICT;

-- 포인트
DROP TABLE IF EXISTS `POINT` RESTRICT;

-- 포인트이력
DROP TABLE IF EXISTS `POINT_HIST` RESTRICT;

-- 포인트부여기준
DROP TABLE IF EXISTS `POINT_VER` RESTRICT;

-- 장소
CREATE TABLE `PLACE` (
	`place_id`   VARCHAR(40)                                       NOT NULL COMMENT '기본키', -- 기본키
	`continent`  SET('asia','europe','africa','america','oceania') NOT NULL COMMENT '대륙', -- 대륙
	`nation`     VARCHAR(100)                                      NOT NULL COMMENT '국가', -- 국가
	`city`       VARCHAR(100)                                      NOT NULL COMMENT '도시', -- 도시
	`place`      VARCHAR(100)                                      NOT NULL COMMENT '장소명', -- 장소명
	`addr`       VARCHAR(255)                                      NOT NULL COMMENT '상세주소', -- 상세주소
	`coordinate` VARCHAR(50)                                       NOT NULL DEFAULT '' COMMENT '좌표값', -- 좌표값
	`reg_date`   DATETIME                                          NOT NULL COMMENT '등록일시' -- 등록일시
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '장소';

-- 장소
ALTER TABLE `PLACE`
	ADD CONSTRAINT `PK_PLACE` -- 장소 기본키
		PRIMARY KEY (
			`place_id` -- 기본키
		);

-- 사용자
CREATE TABLE `USER` (
	`user_id`  VARCHAR(40)  NOT NULL COMMENT '회원아이디', -- 회원아이디
	`name`     VARCHAR(20)  NOT NULL COMMENT '이름', -- 이름
	`birth`    VARCHAR(8)   NOT NULL COMMENT '생년월일', -- 생년월일
	`hp`       VARCHAR(20)  NOT NULL COMMENT '핸드폰', -- 핸드폰
	`sex`      SET('m','f') NULL     COMMENT '성별', -- 성별
	`reg_date` DATETIME     NOT NULL COMMENT '등록일시' -- 등록일시
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '사용자';

-- 사용자
ALTER TABLE `USER`
	ADD CONSTRAINT `PK_USER` -- 사용자 기본키
		PRIMARY KEY (
			`user_id` -- 회원아이디
		);

-- 리뷰
CREATE TABLE `REVIEW` (
	`review_id`   VARCHAR(40)   NOT NULL COMMENT '리뷰', -- 리뷰
	`user_id`     VARCHAR(40)   NOT NULL COMMENT '회원아이', -- 회원아이
	`place_id`    VARCHAR(40)   NOT NULL COMMENT '장소_아이디', -- 장소_아이디
	`content`     VARCHAR(4000) NOT NULL COMMENT '내용', -- 내용
	`reg_date`    DATETIME      NOT NULL COMMENT '등록일시', -- 등록일시
	`point_ver`   TINYINT       NULL     COMMENT '포인트버전', -- 포인트버전
	`modify_date` DATETIME      NULL     COMMENT '수정' -- 수정
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '리뷰';

-- 리뷰
ALTER TABLE `REVIEW`
	ADD CONSTRAINT `PK_REVIEW` -- 리뷰 기본키
		PRIMARY KEY (
			`review_id` -- 리뷰
		);

-- 리뷰 유니크 인덱스
CREATE UNIQUE INDEX `UIX_REVIEW`
	ON `REVIEW` ( -- 리뷰
		`user_id` ASC,  -- 회원아이
		`place_id` ASC  -- 장소_아이디
	);

-- 첨부파일
CREATE TABLE `ADD_FILE` (
	`attache_id` VARCHAR(40)  NOT NULL COMMENT '기본키', -- 기본키
	`refer_id`   VARCHAR(40)  NOT NULL COMMENT '참조', -- 참조
	`file_path`  VARCHAR(200) NOT NULL COMMENT '첨부파일', -- 첨부파일
	`reg_date`   DATETIME     NOT NULL COMMENT '등록일시' -- 등록일시
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '첨부파일';

-- 첨부파일
ALTER TABLE `ADD_FILE`
	ADD CONSTRAINT `PK_ADD_FILE` -- 첨부파일 기본키
		PRIMARY KEY (
			`attache_id` -- 기본키
		);

-- 포인트
CREATE TABLE `POINT` (
	`user_id` VARCHAR(40) NOT NULL COMMENT '회원아이디', -- 회원아이디
	`point`   INT         NOT NULL DEFAULT 0 COMMENT '누적포인트' -- 누적포인트
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '포인트';

-- 포인트
ALTER TABLE `POINT`
	ADD CONSTRAINT `PK_POINT` -- 포인트 기본키
		PRIMARY KEY (
			`user_id` -- 회원아이디
		);

-- 포인트이력
CREATE TABLE `POINT_HIST` (
	`id`          INT                                NOT NULL COMMENT '기본키', -- 기본키
	`proc_type`   SET('CR','CD','FA','FD','IW','ID') NULL     COMMENT '행위코드', -- 행위코드
	`point`       TINYINT                            NOT NULL COMMENT '증감값', -- 증감값
	`reg_date`    DATETIME                           NOT NULL COMMENT '등록일시', -- 등록일시
	`user_id`     VARCHAR(40)                        NOT NULL COMMENT '회원아이', -- 회원아이
	`refer_id`    VARCHAR(40)                        NOT NULL COMMENT '참조데이터 아이디', -- 참조데이터 아이디
	`refer_table` VARCHAR(20)                        NOT NULL COMMENT '참조데이터 테이블' -- 참조데이터 테이블
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '포인트이력';

-- 포인트이력
ALTER TABLE `POINT_HIST`
	ADD CONSTRAINT `PK_POINT_HIST` -- 포인트이력 기본키
		PRIMARY KEY (
			`id` -- 기본키
		);

ALTER TABLE `POINT_HIST`
	MODIFY COLUMN `id` INT NOT NULL AUTO_INCREMENT COMMENT '기본키';

ALTER TABLE `POINT_HIST`
	AUTO_INCREMENT = 1;

-- 포인트부여기준
CREATE TABLE `POINT_VER` (
	`version`        TINYINT  NOT NULL COMMENT '버전', -- 버전
	`content_val`    TINYINT  NOT NULL COMMENT '내용점수', -- 내용점수
	`content_length` TINYINT  NOT NULL COMMENT '내용최소길이', -- 내용최소길이
	`add_file_val`   TINYINT  NOT NULL COMMENT '첨부파일점수', -- 첨부파일점수
	`add_file_cnt`   TINYINT  NOT NULL COMMENT '최소첨부파일갯수', -- 최소첨부파일갯수
	`add_val`        TINYINT  NOT NULL COMMENT '추가점수', -- 추가점수
	`reg_date`       DATETIME NOT NULL COMMENT '등록일시' -- 등록일시
)
DEFAULT CHARACTER SET = 'utf8'
DEFAULT COLLATE = 'utf8_general_ci'
ENGINE = InnoDB
COMMENT '포인트부여기준';

-- 포인트부여기준
ALTER TABLE `POINT_VER`
	ADD CONSTRAINT `PK_POINT_VER` -- 포인트부여기준 기본키
		PRIMARY KEY (
			`version` -- 버전
		);

ALTER TABLE `POINT_VER`
	MODIFY COLUMN `version` TINYINT NOT NULL AUTO_INCREMENT COMMENT '버전';
	
INSERT INTO db_erp_support.POINT_VER (content_val, content_length, add_file_val, add_file_cnt, add_val, reg_date) 
VALUES(1, 1, 1, 1, 1, NOW());

