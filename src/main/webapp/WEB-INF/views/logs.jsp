<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <link rel="stylesheet" href="/resources/static/css/board.css">
</head>
<body>
    <div class="container">
        <!-- 접속 기록 팝업 -->
        <div class="logs-popup">
            <div class="editor">
                <div class="input-box">
                    <label for="studentsName">접속 IP : </label>
                    <input id="ip" type="text" value="192.168.52.43" readonly>
                </div>
                <div class="input-box">
                    <label for="title">접속 시간 : </label>
                    <input id="createAt" type="text" value="2022-06-02 09:10:58" readonly>
                </div>
                <div class="input-box">
                    <!--카카오맵 위치-->
                    <div id="map" style="width:100%; height:300px;"></div>
                </div>
                <div class="btn-area">
                    <a href="#" class="btn-cancel">닫기</a>
                </div>
            </div>
        </div>
        <div class="navigation">
            <ul>
                <li>
                    <a href="/board?pageNum=1&pageSize=10">
                        <span class="icon"><ion-icon name="logo-apple"></ion-icon></span>
                        <span class="title">DW Board</span>                
                    </a>
                </li>
                <li>
                    <a href="/board?pageNum=1&pageSize=10">
                        <span class="icon"><ion-icon name="home-outline"></ion-icon></span>
                        <span class="title" onclick = "goPage('Dashboard')">Dashboard</span>                
                    </a>
                </li>
                <li>
                    <a href="/students">
                        <span class="icon"><ion-icon name="person-outline"></ion-icon></span>
                        <span class="title" onclick= "goPage('Students')">Students</span>                
                    </a>
                </li>
                <li>
                    <a href="/logs">
                        <span class="icon"><ion-icon name="lock-closed-outline"></ion-icon></span>
                        <span class="title" onclick= "goPage('logs')">logs</span>                
                    </a>
                </li>
                <li>
                    <a href="#">
                        <span class="icon"><ion-icon name="log-out-outline"></ion-icon></span>
                        <span class="title">Sign Out</span>                
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- main -->
    <div class="main">
        <div class="topbar">
            <div class="toggle">
                <!-- toggle은 나중에 만들기 -->
                <ion-icon name="menu-outline"></ion-icon>
            </div>
            <!-- search -->
            <div class="search">
                <label>
                    <!-- <input id="searchBar" type="text" placeholder="작성자를 검색하세요..." > -->
                </label>
            </div>
            <div>
                <a href="/logout" class="logout">Logout</a>
            </div>
        </div>
         <!-- table -->
         <div class="details">
             <div class="recentOrders">
                 <div class="cardHeader">
                     <h2>접속자 히스토리</h2>
                 </div>
                 <table>
                     <thead>
                         <tr>
                            <th>로그 번호</th>
                            <th>IP</th>
                            <th>요청 URL</th>
                            <th>HTTP Method</th>
                            <th>접속 날짜</th>
                         </tr>
                     </thead>
                     <tbody id="boardData">
                         <!-- <tr onclick="getPopup()">
                            <td>1</td>
                            <td>192.158.0.252</td>
                            <td>/board</td>
                            <td>GET</td>
                            <td>2022-05-19 13:33:02</td>
                         </tr>
                         <tr onclick="getPopup()">
                            <td>2</td>
                            <td>192.158.0.252</td>
                            <td>/board</td>
                            <td>GET</td>
                            <td>2022-05-19 13:33:02</td>
                        </tr>
                        <tr onclick="getPopup()">
                            <td>3</td>
                            <td>192.158.0.252</td>
                            <td>/board</td>
                            <td>GET</td>
                            <td>2022-05-19 13:33:02</td> -->
                        </tr>
                     </tbody>
                 </table>
                 <div class="pagination">
                    <!-- <a href="#">Previous</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">Next</a> -->
                 </div>
             </div>
         </div>
    </div>
</body>
<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script
      src="https://code.jquery.com/jquery-3.6.0.min.js"
      integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
      crossorigin="anonymous"
    ></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=4e19bfd6ddf4b432f094170886f18019"></script>
<script>
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) => {item.classList.remove('hovered')});
        this.classList.add('hovered');
    }
    list.forEach((item) => {item.addEventListener('mouseover',activeLink)});
</script>
<script>
    $('.logs-popup').css('display', 'none');

    // onclick 팝업창 띄우기(지도)
    function getPopup(logId){
        $('.logs-popup').css('display', 'block');
        
        $.ajax({
        url : '/api/v1/logs/logId/'+logId,
        type : 'GET',
        dataType : 'json', 
        success : function(response){
            var latitude = response.latitude; //위도
            var longitude = response.longitude; //경도
            $('#ip').val(response.ip); 
            $('#createAt').val(response.create_at);
            //카카오맵 
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
               mapOption = { 
                   center: new kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표 (마크 찍기)
                   level: 3 // 지도의 확대 레벨
               };
    
           var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    
           // 마커가 표시될 위치입니다 
           var markerPosition  = new kakao.maps.LatLng(latitude, longitude); 
    
           // 마커를 생성합니다
           var marker = new kakao.maps.Marker({
               position: markerPosition
           });
           // 마커가 지도 위에 표시되도록 설정합니다
           marker.setMap(map);
            }

        });

    }

    $('.btn-cancel').click(function(){
        $('.logs-popup').css('display', 'none');
     });

</script>
<script>

// 로그 기록 조회
getBoardList(1, 10); // 함수 호출 (없으면 결과 안 뜸)

    function getBoardList(pageNum, pageSize){
    $.ajax({
        url : '/api/v1/logs?pageNum='+pageNum+'&pageSize='+pageSize,
        type : 'GET',
        dataType : 'json',
        success : function(response){ 
            //console.log(response);
        var html = "";
        var len = response.list.length;
        if(len>0){  
        for(var i=0; i<len; i++){ // 
            if(len > 0){
                html += 
            '<tr onclick=getPopup('+response.list[i].log_id+')><td>'  // 행 한줄 전체를 클릭할때 map popup 뜨게
                +response.list[i].log_id+
                '</td><td>'
                +response.list[i].ip+
                '</td><td>'
                +response.list[i].url+
                '</td><td>'
                +response.list[i].http_method+
                '</td><td>'
                +response.list[i].create_at+
                '</td></tr>'
                // '</tr></tr>'
                }
    
              
            }// end for
            // 페이징 화면 구현
            var paginationHtml = '';
            if(response.hasPreviousPage){ // 이전 페이지가 있다면(true)
                paginationHtml  += '<a <a onclick="getBoardList('+(response.pageNum-1)+', '+pageSize+')" href="#">Previous</a>';
            }
            for(var i=0; i<response.navigatepageNums.length; i++){ // 페이지 번호 길이 만큼 for문 실행 / navigatepageNums : array
                paginationHtml += '<a id="pageNum'+response.navigatepageNums[i]+'" onclick="getBoardList('+response.navigatepageNums[i]+', '+pageSize+')" href="#">'+response.navigatepageNums[i]+'</a>';
                // id = pageNum1
            }
            if(response.hasNextPage){ // 다음 페이지가 있다면(true)
                paginationHtml  += '<a onclick="getBoardList('+(response.pageNum+1)+', '+pageSize+')" href="#">Next</a>';
            }
            $('.pagination').children().remove(); 
            $('.pagination').append(paginationHtml);

            // 페이지 번호에 맞게 css 수정 
            // 성형작업 2-1
            $('#pageNum'+pageNum).css('backgroundColor','#287bff'); // 파라미터로 클릭한 페이지 번호를 받아오기 때문에 색상이 변경될 수 있음
            $('#pageNum'+pageNum).css('color','#fff')

        }else{
            html += '<tr><td colspan=6 style= "text-align: center;"> 게시글이 없습니다.</tr></td>'
        } // colspan = 6  : 목록이 6개
        
        $('#boardData').children().remove();  
        $('#boardData').append(html); // tbody에 json데이터 렌더링
            },
        });
    }   // end function


</script>

</html>