const userObject = {
	init:function() {
		$('#btn-save').on('click', (e) => {
			e.preventDefault();
			this.insertUser();
		})
	},
	
	insertUser:function() {
		alert("회원가입요청");
		// 회원가입 시 입력한 정보를 추출
		let user = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/auth/insertuser",
			data: JSON.stringify(user),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			if(response.status == 200) {
				alert(response.data);
				location.href = "/";
			} else {
				const result = response.data;
				let msg = '';
				
				if(result.username !=null)
					msg += result.username + "\n";
				if(result.password !=null)
					msg += result.password + "\n";
				if(result.email !=null)
					msg += result.email;
				
				alert(msg);
			}			
		}).fail(function(error) {
			console.log(error);
		})
		
		
		
	}
}

userObject.init();


$("#btn-update").on('click', (e) => {
	e.preventDefault();
	
	if(!confirm("회원 정보를 수정하시겠습니까?"))
		return;
	
	let user = {
		id:$("#id").val(),
		password:$("#password").val(),
		email:$("#email").val()
	}
	
	$.ajax({
		type:"PUT",
		url:"/auth/update",
		data:JSON.stringify(user),
		contentType:"application/json; charset=utf-8"
	}).done(function(response) {
		alert(response.data);
		
		location.href = "/";
	}).fail(function(error){
		console.log(error);
	})
})

$("#btn-delete").on("click", (e) => {
	e.preventDefault();
	
	if(!confirm("회원 탈퇴 하시겠습니까?"))
		return;
	
	let id = $("#id").val();
	
	$.ajax({
		type: "DELETE",
		url: "/auth/delete?id=" + id
	}).done(function(response) {
		alert(response.data);
		
		location.href = "/";
	}).fail(function(error) {
		console.log(error);
	})
})























