/* 
투표 페이지 나오게 하려면 데이터 필요해서 임시로 작성한 데이터 추후 삭제예정

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Do or Die', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/94/944/994944_20231006163709_500.jpg', '뮤직비디오 URL 1', '임영웅', 30,1);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Either Way', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/030/55/146/3055146_20231013113531_500.jpg', '뮤직비디오 URL 1', 'IVE (아이브)', 30,2);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'You&', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/009/95/173/995173_20231005174025_500.jpg', '뮤직비디오 URL 1', '제니 (JENNIE)', 30,3);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, '3D (feat. Jack Harlow)', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/007/25/987/725987_20231103102819_500.jpg', '뮤직비디오 URL 1', '정국', 30,4);


INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, '잠시라도 우리', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/000/03/305/3305_20210521133532_500.jpg', '뮤직비디오 URL 1', '성시경, 나얼', 30,5);


INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Chili', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/007/56/531/756531_20230830113847_500.jpg', '뮤직비디오 URL 1', '화사 (HWASA)', 30,6);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'GODS', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/031/14/174/3114174_20230707095732_500.jpg', '뮤직비디오 URL 1', 'NewJeans', 30,7);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Fact Check (불가사의; 不可思議)', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/031/14/174/3114174_20230707095732_500.jpg', '뮤직비디오 URL 1', 'NCT 127', 30,8);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Somebody', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/006/72/853/672853_20230913112522_500.jpg', '뮤직비디오 URL 1', '디오 (D.O.)', 30,9);

INSERT INTO product (product_No, product_category_id, product_Name, product_Price, dtype, product_Content, product_Star, product_Date, read_Count, product_Stock, product_Image, product_Movie, product_Artist, period_of_use, vote_id)
VALUES (product_product_no_seq.nextval, 1, 'Chasing That Feeling', 100, 'music', '음악 제품 설명 1', 0 , TO_DATE('2023-10-23', 'YYYY-MM-DD'), 100, 50, 'https://cdnimg.melon.co.kr/cm2/artistcrop/images/026/32/253/2632253_20231013103136_500.jpg', '뮤직비디오 URL 1', '투모로우바이투게더', 30,10);

commit;


*/