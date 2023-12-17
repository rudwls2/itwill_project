$(document).ready(function() {
  var orderHistory = $('#orderHistory');

  // 정렬 함수
  function sortItems(order) {
    var items = orderHistory.find('.e').get();
    console.log(items)
    items.sort(function(a, b) {
      var dateA = new Date($(a).find('.describe span').text().replace('구매날짜: ', ''));
      var dateB = new Date($(b).find('.describe span').text().replace('구매날짜: ', ''));
	console.log(dateA)
	console.log(dateB)
      // 날짜 객체를 사용하여 비교
      if (order === 'newest') {
        return dateB - dateA;
      } else {
        return dateA - dateB;
      }
    });
console.log(items)
    orderHistory.empty();

    // 정렬된 배열에 대한 순회
    for (var i = 0; i < items.length; i++) {
      orderHistory.append(items[i]);
      // 'div.movie-item-style-2' 다음에 'div.movie-item-style-2.movie-item-style-3' 추가
      if (i % 2 !== 0 && i + 1 < items.length) {
        orderHistory.append(items[i + 1]);
      }
    }
  }

  // 선택 요소의 변경 이벤트를 감지
  $('#sortSelect').change(function() {
    var selectedOption = $(this).val();
    sortItems(selectedOption);
  });

  // 페이지 로드 시 최신순으로 정렬
  sortItems('newest');
});


/////////삭제버튼/////////////

document.addEventListener("DOMContentLoaded", function() {
    // 삭제 버튼을 클릭할 때
    document.querySelectorAll(".deleteButton").forEach(function(button) {
        button.addEventListener("click", function() {
            var orderId = button.getAttribute("data-order-id");
            
            console.log(orderId)
            // AJAX 요청을 보냅니다.
            var xhr = new XMLHttpRequest();
            // 주문 ID를 동적으로 대체
       		var url = '/2023-05-JAVA-DEVELOPER-final-project-team1-mango/order/delete/'+ orderId; // 결제 처리를 수행하는 서버 엔드포인트 URL
            xhr.open("DELETE", url, true);
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.onreadystatechange = function() {
			    if (xhr.readyState === 4) {
			        if (xhr.status === 200) {
			            alert("주문내역이 삭제되었습니다.");
			            // 페이지 새로고침 또는 다른 업데이트 작업 수행
			               window.location.reload();
			        } else {
			            alert("주문내역 삭제 중 오류가 발생했습니다.");
			        }
			    }
			};
            // 요청 데이터를 동적으로 설정
			xhr.send(JSON.stringify({ orderId: orderId }));
        });
    });
});


/////최신순, 오래된순///////
/*  document.getElementById("sortSelect").addEventListener("change", function() {
        // 선택된 옵션 값 확인
        var selectedValue = this.value;

        // 선택된 값에 따라 동적으로 처리
        switch (selectedValue) {
            case "newest":
                // 최신순에 대한 동작 추가
                var order = button.getAttribute("newest");
                console.log(order)
                alert("최신순을 선택했습니다.");
                break;
            case "oldest":
                // 오래된순에 대한 동작 추가
                alert("오래된순을 선택했습니다.");
                break;
            default:
                // 다른 경우에 대한 처리 추가
                break;
        }
    });*/