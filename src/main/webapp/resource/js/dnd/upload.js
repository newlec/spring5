window.addEventListener("load", function(){
    var section = document.querySelector("#upload");
    var dropZone = section.querySelector(".drop-zone");
    var percentSpan = section.querySelector(".percent");
    var progressDiv = section.querySelector(".progress");

    dropZone.addEventListener("dragenter", function(e){
        e.preventDefault();
        console.log("들어왔니?");
        console.log(e.dataTransfer.types[0]); // Files, text/plain

        if(e.dataTransfer.types[0] == "Files"){
            dropZone.classList.add("valide");
            dropZone.classList.remove("invalide");
        }
        else{
            dropZone.classList.remove("valide");
            dropZone.classList.add("invalide");
        }
    });

    dropZone.addEventListener("dragleave", function(e){
        e.preventDefault();
        console.log("나갔니?");
    });

    dropZone.addEventListener("dragover", function(e){
        e.preventDefault();
        console.log("위에 있니?");
    });

    dropZone.addEventListener("drop", function(e){
        e.preventDefault();
        console.log("드랍");

        
        var files = e.dataTransfer.files;
        var size = files.length;
        
        if(size > 1){
            alert("파일은 하나씩만 업로드 할 수 있습니다.");
            return;
        }

        var file = files[0];
        /*console.log(file.type); // 파일 종류
        var regex = new RegEx("image/(jpeg|png|gif)");
        //if(!file.type.match(/image/(jpeg|png|gif)/)){
        if(!file.type.match(regex)){
            alert("유효한 파일 형식 아닙니다.");
            return;
        }*/

        /*if(file.size > 10*1024*1024 ){
            alert("10메가 이상의 파일은 업로드 할 수 없습니다.");
            return;
        }      */ 

        // id=newlec&pwd=111 -> only String
        var formData = new FormData();
        formData.append("file", file);
        
        var request = new XMLHttpRequest();
        request.addEventListener("load", function(e){
        	if(request.responseText == "okay"){
        		// 목록을 새로 요청하기
        		var request = new XMLHttpRequest();
        		equest.addEventListener("load", function(e){
        			alert(e.responseText);
        		});
        		request.open("GET", "../../../file-list");
                request.send();

        	}
        		
        });
        request.upload.addEventListener("progress", function(e){
        	// loaded, total
        	if(e.lengthComputable){
        		var degree = Math.round(e.loaded * 100 / e.total);
        		percentSpan.innerText = degree;
        		progressDiv.style.width = degree+"%";
        	}
        	//else
        		//progressBoxDiv.innerText = "전송크기를 계산할 수 없습니다.";
        	
        	
        	
        });
        request.open("POST", "../../../upload");
        request.send(formData); // 동기형으로 하면 여기서 멈춤현상

        /*
        load
        error
        abort
        uploadProgress 
         
         */
        
        


    });

    /*
    //drop zone에서 사용되는 이벤트
    dragenter : 드래된 리소스가 drop zone에 들어올 때
    dragleave : drop zone에 들어왔던 드래그가 영역 밖으로 나갈 때
    dragover  : drop zone에서 드래그 상태로 이동 중일 때
    drop      : drop zone에서 드랍할 때
    */
});