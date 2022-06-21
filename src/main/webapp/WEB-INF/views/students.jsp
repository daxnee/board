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
        <div class="write-popup">
            <div class="editor">
                <div class="close">
                    <a href="#" class="btn-close">닫기</a>
                </div>
                <div class="input-box">
                    <label for="studentsId">학생 아이디 : </label>
                    <input id="upt-id" type="text" placeholder="아이디 입력하세요...">
                </div>
                <div class="input-box">
                    <label for="studentsName">학생 이름 : </label>
                    <input id="upt-name" type="text" placeholder="이름을 입력하세요...">
                </div>
                <div class="input-box">
                    <label for="studentsPassword">학생 비밀번호 : </label>
                    <input id="upt-password" type="text" placeholder="이름을 입력하세요...">
                </div>  
                <div class="btn-area">
                    <input id="studentsIdHidden" type="hidden">
                    <a id="contentSubmit" href="#" class="btn-success">등록</a>
                </div>
            </div> 
        </div>
        <!-- 학생 정보 수정 -->
        <div class="update-popup">
            <div class="editor">
                <div class="close">
                    <a href="#" class="btn-close">닫기</a>
                </div>
                <div class="input-box">
                    <label for="studentsId">학생 아이디 : </label>
                    <input id="studentsId" type="text" placeholder="아이디 입력하세요...">
                </div>
                <div class="input-box">
                    <label for="studentsName">학생 이름 : </label>
                    <input id="studentsName" type="text" placeholder="이름을 입력하세요...">
                </div>
                <div class="input-box">
                    <label for="studentsPassword">학생 비밀번호 : </label>
                    <input id="studentsPassword" type="text" placeholder="이름을 입력하세요...">
                </div>  
                <div class="btn-area">
                    <input id="boardIdHidden" type="hidden">
                    <a id="contentUpdate" href="#" class="btn-update">수정</a>
                    <a id="contentDelete" href="#" class="btn-delete">삭제</a>
                </div>
            </div>
        </div>
        <div class="navigation">
            <ul>
                <li>
                    <a href=""/board?pageNum=1&pageSize=10"">
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
                    <input id="searchBar" type="text" placeholder="학생이름을 검색하세요..." >
                     <input id="keyword" type="hidden" value="null" />
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
                     <h2>학생 명단</h2>
                     <a onclick="addStudents()" id="students-btn" href="#" class="학생 추가">학생 추가</a>
                 </div>
                 <table>
                     <thead>
                         <tr>
                            <th>학생 아이디</th>
                            <th>학생 이름</th>
                            <th>가입 날짜</th>
                        </tr>
                     </thead>
                     <tbody id="boardData">
                         <!-- <tr> -->
                             <!-- <td>hyunsangwon</td>
                             <td>현상원</td>
                             <td>2022-05-19</td>
                         </tr>
                         <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td>
                        </tr>
                        <tr>
                            <td>hyunsangwon</td>
                            <td>현상원</td>
                            <td>2022-05-19</td> -->
                        <!-- </tr> -->
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
<script>
    $('.btn').click(function(){
        $('.write-popup').css('display', 'block');
    });
    $('.btn-cancel').click(function(){
        $('.write-popup').css('display', 'none');
    });
    $('.btn-close').click(function(){
        $('.update-popup').css('display','none');
    })
    let list = document.querySelectorAll('.navigation li');
    function activeLink(){
        list.forEach((item) => {item.classList.remove('hovered')});
        this.classList.add('hovered');
    }
    list.forEach((item) => {item.addEventListener('mouseover',activeLink)});
</script>
<script>

    //학생 리스트 호출
 
   
    //상세내용 확인 함수
    function getStudents(studentsId){
        $('.update-popup').css('display', 'block');
        $.ajax({
            url : '/api/v1/students/id/'+studentsId,
            type : 'GET',
            dataType : 'json', 
            success : function(response){
            //3. input에 데이터 set 해주기
            $('#studentsId').val(response.studentsId);
            $('#studentsName').val(response.studentsName);
            $('#studentsPassword').val(response.studentsPassword);
            // $('#boardIdHidden').val(response.boardId); 
         
            }
        });
    }; //end getStudents()

	getStudentsList(1, 5); // 함수 호출 (없으면 결과 안 뜸)

		function getStudentsList(pageNum, pageSize){
		$.ajax({
	    url : '/api/v1/students/map?pageNum='+pageNum+'&pageSize='+pageSize,
	    type : 'GET',
	    dataType : 'json',
	    success : function(response){ 
	        console.log(response.list);
	    var html = '';
	    var len = response.list.length;
	    if(len > 0){  
	    for(var i=0; i<len; i++){ // 
	        if(len > 0){         
	            html += 
	        '<tr onclick=getStudents('+response.list[i].studentsId+')><td>'  
	            +response.list[i].studentsId+
	            '</td><td>'
	            +response.list[i].studentsName+
	            '</td><td>'
	            +response.list[i].createAt+
	             '</td></tr>'
	            }
	        }// end for

        // 페이징 화면 구현
	        var paginationHtml = '';
	        if(response.hasPreviousPage){ // 이전 페이지가 있다면(true)
	            paginationHtml  += '<a onclick="getStudentsList('+(response.pageNum-1)+', '+pageSize+')" href="#">Previous</a>';
	        }
	        for(var i=0; i<response.navigatepageNums.length; i++){ // 페이지 번호 길이 만큼 for문 실행 / navigatepageNums : array
	            paginationHtml += '<a id="pageNum'+response.navigatepageNums[i]+'" onclick="getStudentsList('+response.navigatepageNums[i]+', '+pageSize+')" href="#">'+response.navigatepageNums[i]+'</a>';
	            // id = pageNum1
	        }
	        if(response.hasNextPage){ // 다음 페이지가 있다면(true)
	            paginationHtml  += '<a onclick="getStudentsList('+(response.pageNum+1)+', '+pageSize+')" href="#">Next</a>';
	        }
	        $('.pagination').children().remove(); // 성형작업1
	        $('.pagination').append(paginationHtml);
	
	        // 페이지 번호에 맞게 css 수정 
	        // 성형작업 2-1
	        $('#pageNum'+pageNum).css('backgroundColor','#287bff'); // 파라미터로 클릭한 페이지 번호를 받아오기 때문에 색상이 변경될 수 있음
	        $('#pageNum'+pageNum).css('color','#fff')
	
	
		    }else{
		        html += '<tr><td colspan=3 style= "text-align: center;"> 게시글이 없습니다.</tr></td>'
		    } 
		    
		    $('#boardData').children().remove();  // 성형작업1
		    $('#boardData').append(html); // tbody에 json데이터 렌더링
		    },
		});
		}   // end function



	    //학생 추가
	    $(".btn-insert").click(function () {
      var studentsName = $("#insertStudentsName").val();
      var studentsPassword = $("#insertStudentsPassword").val();
      var jsonData = {
        studentsName: studentsName,
        studentsPassword: studentsPassword,
      };
      if (studentsName == "" || studentsPassword == "") {
        alert("학생 정보를 입력해주세요!");
        if (studentsName == "") {
          $("#insertStudentsName").focus();
        } else {
          $("#insertStudentsPassword").focus();
        }
        return false;
      }
      if (confirm("학생을 추가 하시겠습니까?")) {
        $.ajax({
          url: "/api/v1/students",
          type: "POST",
          contentType: "application/json", //서버에 json타입으로 보낼 예정(요청).
          dataType: "json", //서버결과를 json으로 응답받겠다.
          data: JSON.stringify(jsonData),
          success: function (response) {
            if (response > 0) {
              alert("학생이 추가 되었습니다.");
              $("#insertStudentsName").val("");
              $("#insertStudentsPassword").val("");
              $(".insert-popup").css("display", "none");
              location.reload();
            }
          },
          error: function (request, statis, error) {
            console.log(error);
          },
        });
      }
    });

    
    
    // 학생 수정
    	$('#contentUpdate').click(function(){
	        var boardIdHidden = $('#boardIdHidden').val(); // hidden에 숨겨둔 boardId 가져오기
	        var studentsId = $('#studentsId').val();
	        var studentsName = $('#studentsName').val();
	        var studentsPassword = $('#studentsPassword').val();
	
	        var jsonData = {
	            studentsId : studentsId,
	            studentsName : studentsName,
	            studentsPassword : studentsPassword
	        };

	      $.ajax({ 
	            url : '/api/v1/students/id/'+studentsId,
	            type : 'PATCH', 
	            contentType : 'application/json', // 서버에 json타입으로 보낼 예정(요청)
	            dataType : 'json', // 서버 결과를 json으로 응답받겠다
	            data : JSON.stringify(jsonData),
	            success : function(response){
	                if(response > 0){
	                    if(!confirm('수정 하시겠습니까?')){
	                        return false; 
	                    }
	                    alert('수정 완료');
	                    $('#boardData').children().remove();
	                    $('.update-popup').css('display', 'none'); // 게시물 상세 화면 감추기
	                    location.reload();
	                }
	            }
	        }); //ajax end 
	    });


    	// 학생 삭제
    	 $("#contentDelete").click(function(){
        var studentsId = $("#studentsIdHidden").val();
        if(confirm("학생이 작성한 게시글은 DB에 남아있습니다.\n삭제하시겠습니까?")){ // \n : 줄바꿈
            $.ajax({
                url : "/api/v1/students/id/" + studentsId,
                type : "DELETE",
                dataType : "json",
                success : function(response){
                    alert('삭제 완료');
                    $('.update-popup').css('display','none');
                    $("#boardData").children().remove();
                    location.reload();
                },
            });
        }
    }); //end function
    
    
	// students search
    $('#searchBar').keyup(function(key){
        var pageNum = 1;
        var pageSize = 10;
          if(key.keyCode == 13){
     	     var writer = $('#searchBar').val().trim();
           		if(writer != ''){
            		location.href="/students/search?studentsName="+studentsName+"&pageNum="+pageNum+"&pageSize="+pageSize;
            	}
              }
      	    })



 
    
 
   
</script>
</html>