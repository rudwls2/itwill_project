INSERT INTO board_type(board_type_id,board_type_title)
VALUES(1,'기타');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(2,'일반공지');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(3,'서비스공지');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(4,'시스템공지');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(5,'회원정보');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(6,'결제/환불');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(7,'이벤트');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(8,'답변완료');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(9,'답변대기중');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(10,'진행중이벤트');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(11,'종료된이벤트');
INSERT INTO board_type(board_type_id,board_type_title)
VALUES(12,'MAGAZINE');

INSERT INTO board_category (board_category_id,board_category_name)
VALUES (1, '공지사항');
INSERT INTO board_category (board_category_id, board_category_name)
VALUES (2, '이벤트');
INSERT INTO board_category (board_category_id, board_category_name)
VALUES (3, '매거진');
INSERT INTO board_category (board_category_id, board_category_name)
VALUES (4, '1대1문의');
INSERT INTO board_category (board_category_id, board_category_name)
VALUES (5, 'FAQ');

INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                1,   
                3,    
                216, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '망고뮤직서비스안정화공지입니다', 'images/board/notifition/image_notifition_1.jpg', '서비스이용안내해드립니다...',null,'admin');
                
INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                1,   
                2,    
                781, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '안녕하세요 망고뮤직입니다 대한민국 대표음악시상식 MMA가 개최됩니다!', 'images/board/notifition/image_notifition_2.jpg', 'MMA2023 개최 안내',null,'admin');


INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                2,   
                10,    
                9871, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '11월에 20% 할인쿠폰을 드립니다', 'images/board/event/event1.jpg', '모든유저 할인쿠폰 발급안내','할인쿠폰','admin');
                
INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                2,   
                11,    
                10946, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '2023새해를 기념해서 멤버쉽을 30%할인된 가격에 만나실 수 있습니다.', 'images/board/event/event2.jpg', '2023 새해기념 이벤트','멤버쉽할인','admin');




INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                3,   
                12,
                4101, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '최애의 사진 한 장 한 장이, 더욱 소중해진
코시국의 2021년...

그런데 이번 멜뮤(2021 멜론뮤직어워드)에서
레전드 갱신한 최애 사진들이 업데이트됐다구?

오직 멜뮤에서만 볼 수 있던
아름다운 최애의 순간들.

오래오래 간직하라고
배경화면 만들어봤음!
소중했던 기억을 되새기며
즐겨주세요! ♥

4년 만에 멜뮤 돌아오더니
단숨에 5관왕 휩쓸고 간 아이유 언니.

'strawberry moon' 무대를 처음 공개하며
환상의 세계에 초대한 것도 모자라

초기 곡인 '잔소리' '좋은 날'의 작사가인
김이나 작가에게 "송 라이팅 상"을 전해 받으며
아티스트로서의 완벽한 서사를 만들었다..☆

아이유 님 MMA 강림 현장,
가사와 함께 휴대폰에 담아 오래 간직하자!

작사, 작곡 장인이자 음원 차트 킬러로,
멜뮤에도 올 출석하시는 헤이즈 언니ㅠㅠ

올해도 감성으로
멜뮤를 찢고 가셨다.
쌓이는 눈처럼
내 맘에도 송송 내려앉는
헤이즈의 감성 모멘트,
배경화면으로도 만들어봤음! ', 'images/board/notifition/image_notifition_2.jpg', 'MMA 포토로 핸드폰 배경화면을 만들어보자! (배경화면 나눔)
',null,'admin');

INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                3,   
                12,
                9999, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '파이썬마스터의 길은 어떤길일까... 과연 ', 'images/board/notifition/image_notifition_2.jpg', 'ITWILL MAGAZNIE',null,'admin');

VALUES (board_board_no_seq.nextval, 
                3,   
                12,
                13504, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '프로젝트마스터의 길은 어떤길일까... 과연 ', 'images/board/notifition/image_notifition_2.jpg', 'ITWILL MAGAZNIE',null,'admin');

INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                3,   
                12,
                196, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '통계마스터의 길은 어떤길일까... 과연 ', 'images/board/notifition/image_notifition_2.jpg', '데이터과학이란',null,'admin');

INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    user_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize)
VALUES (board_board_no_seq.nextval, 
                4,   
                8,
                'lsg33',
                2, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '환불해주세요!!환불해주세요!!환불해주세요!!환불해주세요!!환불해주세요!!환불해주세요!!', 'images/board/notifition/image_notifition_2.jpg', '환불해주세요!!',null);



INSERT INTO user_board (user_board_id, board_id, user_id)
VALUES (USER_BOARD_NO_SEQ.nextval, 1, 'why3795');
INSERT INTO user_board (user_board_id, board_id, user_id)
VALUES (USER_BOARD_NO_SEQ.nextval, 2, 'myr1109');

INSERT INTO BOARD_REPLY(board_reply_id,
                                                board_id,
                                                user_id,
                                                create_date_time,
                                                board_reply_title,board_reply_content)
VALUES(board_reply_no_seq.nextval,
              board_board_no_seq.currval,
              'myr1109',
              SYSTIMESTAMP,
              '환불문의답변드립니다','차경진씨에게문의하세요');
              
INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    user_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize)
VALUES (board_board_no_seq.nextval, 
                4,   
                9,
                'why3795',
                0, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '배송이 도대체 언제오는건가요?벌써 5달이나 기다렸습니다', 'images/board/notifition/image_notifition_2.jpg', '배송이안와요 ㅠㅠ',null);

INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                5,   
                5,    
                0, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '비밀번호는 아이디와 이름으로 찾을수있습니다', 'images/board/faq/faq1.jpg', '아이디와비밀번호찾기...',null,'admin');
                
INSERT INTO BOARD (board_id,
                                    board_category_id,
                                    board_type_id,
                                    BOARD_READ_COUNT,
                                    created_time, update_time, 
                                    board_content, board_image, board_title,board_prize,user_id)
VALUES (board_board_no_seq.nextval, 
                5,   
                6,    
                0, 
                SYSTIMESTAMP, SYSTIMESTAMP, 
                '환불은 해드리지않습니다.', 'images/board/faq/faq2.jpg', '환불관련FAQ...',null,'admin');
              
      
              
commit;