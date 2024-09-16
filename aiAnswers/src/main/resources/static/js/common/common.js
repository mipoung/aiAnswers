function idCheck(id) {
  // 아이디가 5자리 이상 12자리 이하인지 확인
  if (id.length < 5 || id.length > 12) {
	  alert("자릿수를 확인해주세요");
	  $(id).focus();
    return false;
  }

  // 영어 대소문자와 숫자만으로 구성되었는지 확인
  const regex = /^[a-zA-Z0-9]+$/;
  if (!regex.test(id)) {
    return false;
  }
  return true;
}

function chkData(item,msg) {
	if($(item).val().replace(/\s/g,"")=="") {
		alert(msg+" 입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	}else {
		return true;
	}
}

function formCheck(item,message,msg) {
	if($(item).val().replace(/\s/g,"")=="") {
		$(message).text(msg+"입력해 주세요.");
		$(item).val("");
		$(item).focus();
		return false;
	}else {
		return true;
	}
}

/*함수명:chkFile(파일명 객체)
* 설명:이미지 파일 여부를 확인하기 위해 확장자 확인 함수.
if (!chkFile($("#file"))) return; */
function chkFile(item) {
		/**참고사항
		 * jQuery.inArray(찾을 값,검색 대상의 배열) : 배열내의 값을 찾아서 인덱스를 반환(요소가 없을 경우 -1 반환)
		 * pop():배열의 마지막 요소를 제거한 후, 제거한 요소를 반환
		 */
	let ext = item.val().split('.').pop().toLowerCase();
	if(jQuery.inArray(ext,['gif','png','jpg'])== -1) {
		alert('gif,png,jpg 파일만 업로드 할 수 있습니다.');
		item.val("");
		return false;
	}else{
		return true;
	}
}

function checkForm(item,msg) {
	let message="";
	if($(item).val().replace(/\s/g,"")=="") {
		message=msg+" 입력해 주세요.";
		$(item).attr("placeholder",message);
		return false;
	}else{
		return true;
	}
	
}

function showSpinner() {
    $('#loading-spinner').show();
}

function hideSpinner() {
    $('#loading-spinner').hide();
}