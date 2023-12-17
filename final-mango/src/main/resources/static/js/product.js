///////////// MUSIC LIST //////////////////////
/* 검색 기능 */
document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	const musicContainer = document.getElementById('musicList');
	var initialData = $('#musicList').html();

	searchInput.addEventListener('keyup', function(event) {
		//타자 넣을때마다 검색 (key up) -> 검색칸 조건 X
		if (true) {
			const searchValue = searchInput.value;
			console.log(searchValue);
			filterItems(searchValue);
		}
	});

	var itemList = $(initialData).find('.movie-item-style-2').toArray();
	console.log(itemList);

	function filterItems(searchQuery) {
		musicContainer.innerHTML = '';


		itemList.forEach(function(item) {

			var musicName = $(item).find('.musicName').text();
			console.log(musicName);
			var artist = $(item).find('.artist').text();



			if (musicName.toLowerCase().includes(searchQuery.toLowerCase())) {
				musicContainer.appendChild(item);
			}
			if (artist.toLowerCase().includes(searchQuery.toLowerCase())) {
				musicContainer.appendChild(item);
			}
			if (musicName.toLowerCase().startsWith(searchQuery.toLowerCase())) {
				musicContainer.appendChild(item);
			}
			if (artist.toLowerCase().startsWith(searchQuery.toLowerCase())) {
				musicContainer.appendChild(item);
			}
		});

	}
});

/*내림차순 - 필요없어짐*/
/*$(document).ready(function() {
	var initialData = $('#musicList').html();
	var rankCount = $('#rank');
	var musicContainer = $('#musicList');
	
	function sortRank(ascending){
		var itemList = musicContainer.find('.movie-item-style-2').toArray();
		itemList.sort(function(a,b){
			var dataA = new Data($(a).find('p').eq(3).text());
			var dataB = new Data($(b).find('p').eq(3).text());
			return ascending ? dataB - dataA : dataA - dataB;
		});
		musicContainer.empty();
		$.each(itemList, function(index,item){
			musicContainer.append(item);
			
		});
	}
		rankCount.on('change', function() {
		var selectedOption = rankCount.val();
		if (selectedOption === 'latest') {
			sortRank(false); // 최신순으로 정렬
			}
		});
	});
*/






/////////// top button 보이기 | 숨기기////////////////

$(function() { 
	$(window).scroll(function() {
    if ($(this).scrollTop() > 500) { //300 넘으면 버튼이 보여짐
      $('#topBtn').fadeIn();
    } else {
      $('#topBtn').fadeOut();
    }
  }); // 버튼 클릭시 
  $("#topBtn").click(function() { 
  	$('html, body').animate({ scrollTop : 0 // 0 까지 animation 이동
  	}, 400); // 속도 400 
  	return false; 
  }); 
});


/////////////// 로그인 유무? //////////////////

/*function loginChk() {
	let signIn = '<%=(String)session.getAttribute("userId")%>';
	if(signIn==null,""){
		alert("로그인 후 사용하실 수 있습니다.");
		window.location.href = "/2023-05-JAVA-DEVELOPER-final-project-team1-mango/user_login_form";
	}
}*/

///////////// 댓글달기 //////////////////////
/*	function create_reply() {
		const urlParams = new URLSearchParams(window.location.search);
		const productNo = urlParams.get("productNo");
		const productNoInt = parseInt(productNo, 10); //prodctId

		var commentContent = $("#comment_content").val()
		console.log(commentContent);

		const commentData = {
			"productReplyTitle": "댓글타이틀",
			"productReplyContent": commentContent,
			"productNo": productNoInt,
			"userId": "rgh66" //세션처리 아이디넣으면 자동으로 이름들어옴
		}
		console.log(commentData);
		fetch(`/2023-05-JAVA-DEVELOPER-final-project-team1-mango/product_reply_create/${productNo}`, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(commentData)
		})
			.then(response => {
				if (response.status === 201) {
					alert('댓글 등록되었습니다.');
					location.reload(); // 현재 페이지를 새로 고침

				} else {
					alert('댓글 등록에 실패했습니다.');
					alert('에러 발생: ' + error);

				}
			})
			.catch(error => {
				alert('에러 발생: ' + error);
			});


	}*/
///////////GOODS LIST////////////
$(document).ready(function(){
    // Slick 슬라이더 초기화 코드
    $('.your-slider-class').slick();
});
/*goods 검색기능*/
document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	const goodsContainer = document.getElementById('goodsList');
	var initialData = $('#goodsList').html();

	searchInput.addEventListener('keyup', function(event) {
		//타자 넣을때마다 검색 (key up) -> 검색칸 조건 X
		if (true) {
			const searchValue = searchInput.value;
			console.log(searchValue);
			filterItems(searchValue);
		}
	});

	var itemList = $(initialData).find('.ceb-item').toArray();
	console.log(itemList);

	function filterItems(searchQuery) {
		goodsContainer.innerHTML = '';
		
		var itemsAdded = 0;

		itemList.forEach(function(item) {

			var goodsName = $(item).find('.goodsName').text();
								

			if (goodsName.toLowerCase().includes(searchQuery.toLowerCase())) {
				goodsContainer.appendChild(item);
				itemsAdded++;
			}

			if (goodsName.toLowerCase().startsWith(searchQuery.toLowerCase())) {
				goodsContainer.appendChild(item);
				itemsAdded++;
			}
			
		});

	}
});


// goods 리스트 정렬 함수
$(document).ready(function () {
  function sortGoodsItems(order) {
    var goodsList = $('#goodsList');
    var items = goodsList.find('celebrity-items3').toArray();

    items.sort(function (a, b) {
      var aValue, bValue;

      if (order === 'readCountAsc') {
        aValue = parseInt($(a).find('p:contains("조 회 수") span').text());
        bValue = parseInt($(b).find('p:contains("조 회 수") span').text());
        return aValue - bValue;
      } else if (order === 'readCountDesc') {
        aValue = parseInt($(a).find('p:contains("조 회 수") span').text());
        bValue = parseInt($(b).find('p:contains("조 회 수") span').text());
        return bValue - aValue;
      } else if (order === 'dateDesc') {
        aValue = new Date($(a).find('p:contains("등록날짜") span').text());
        bValue = new Date($(b).find('p:contains("등록날짜") span').text());
        return aValue - bValue;
      } else if (order === 'dateAsc') {
        aValue = new Date($(a).find('p:contains("등록날짜") span').text());
        bValue = new Date($(b).find('p:contains("등록날짜") span').text());
        return bValue - aValue;
      }
    });

    goodsList.empty();
    $.each(items, function (i, item) {
      goodsList.append(item);
    });
  }

  // select 요소의 변경 감지
  $('#sortSelect').on('change', function () {
    var selectedOption = $(this).val();
    sortGoodsItems(selectedOption);
  });
});



//////////////////// TICKET LIST //////////////////////
/*티켓 검색기능*/
document.addEventListener('DOMContentLoaded', function() {
	const searchInput = document.getElementById('searchInput');
	const ticketContainer = document.getElementById('ticketList');
	var initialData = $('#ticketList').html();

	searchInput.addEventListener('keyup', function(event) {
		//타자 넣을때마다 검색 (key up) -> 검색칸 조건 X
		if (true) {
			const searchValue = searchInput.value;
			console.log(searchValue);
			filterItems(searchValue);
		}
	});

	var itemList = $(initialData).find('.movie-item-style-2').toArray();
	console.log(itemList);

	function filterItems(searchQuery) {
		ticketContainer.innerHTML = '';


		itemList.forEach(function(item) {

			var ticketName = $(item).find('.ticketName').text();
					

			
			if (ticketName.toLowerCase().includes(searchQuery.toLowerCase())) {
				ticketContainer.appendChild(item);
			}
			if (ticketName.toLowerCase().startsWith(searchQuery.toLowerCase())) {
				ticketContainer.appendChild(item);
			}
			
		});

	}
});



  // 정렬 함수
/*$(document).ready(function () {
  function sortItems(order) {
    var ticketList = $('#ticketList'); // 티켓 목록을 감싸는 요소의 ID를 사용
    var items = ticketList.find('.movie-item-style-2').get(); // 티켓 항목 요소를 가져옴
    items.sort(function (a, b) {
      if (order === 'readCountAsc') {
        var readCountA = parseInt($(a).find('p:contains("조 회 수") span').text());
        var readCountB = parseInt($(b).find('p:contains("조 회 수") span').text());
        return readCountA - readCountB;
      } else if (order === 'readCountDesc') {
        var readCountA = parseInt($(a).find('p:contains("조 회 수") span').text());
        var readCountB = parseInt($(b).find('p:contains("조 회 수") span').text());
        return readCountB - readCountA;
      } else if (order === 'dateDesc') {
        var dateA = new Date($(a).find('p:contains("등록날짜") span').text());
        var dateB = new Date($(b).find('p:contains("등록날짜") span').text());
        return dateA - dateB;
      } else if (order === 'dateAsc') {
        var dateA = new Date($(a).find('p:contains("등록날짜") span').text());
        var dateB = new Date($(b).find('p:contains("등록날짜") span').text());
        return dateB - dateA;
      }
    });
    ticketList.empty();
    $.each(items, function (i, item) {
      ticketList.append(item);
    });
  }*/
  // ticket 리스트 정렬 함수
$(document).ready(function () {
  function sortTicketItems(order) {
    var ticketList = $('#ticketList');
    var items = ticketList.find('.movie-item-style-3').get();

    items.sort(function (a, b) {
      var aValue, bValue;

      if (order === 'readCountAsc') {
        aValue = parseInt($(a).find('p:contains("조 회 수") span').text());
        bValue = parseInt($(b).find('p:contains("조 회 수") span').text());
        return aValue - bValue;
      } else if (order === 'readCountDesc') {
        aValue = parseInt($(a).find('p:contains("조 회 수") span').text());
        bValue = parseInt($(b).find('p:contains("조 회 수") span').text());
        return bValue - aValue;
      } else if (order === 'dateDesc') {
        aValue = new Date($(a).find('p:contains("등록날짜") span').text());
        bValue = new Date($(b).find('p:contains("등록날짜") span').text());
        return aValue - bValue;
      } else if (order === 'dateAsc') {
        aValue = new Date($(a).find('p:contains("등록날짜") span').text());
        bValue = new Date($(b).find('p:contains("등록날짜") span').text());
        return bValue - aValue;
      }
    });

    ticketList.empty();
    $.each(items, function (i, item) {
      ticketList.append(item);
    });
  }

  // select 요소의 변경 감지
  $('#sortSelect').on('change', function () {
    var selectedOption = $(this).val();
    sortTicketItems(selectedOption);
  });
});
