$(document).ready(function() {
	$("#content").summernote({
		height: 400
	});
});

const postObject = {
	init: function() {
		$("#btn-insert").on("click", (e) => {
			e.preventDefault();
			this.insertPost();
		})
		
		$("#btn-update").on("click", (e) => {
			e.preventDefault();
			this.updatePost();
		})
		
		$("#btn-delete").on("click", (e) => {
			e.preventDefault();
			this.deletePost();
		})
	},
	
	insertPost: function() {
		const post = {
			title: $("#title").val(),
			content: $("#content").val()
		};
		
		$.ajax({
			type: "POST",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			alert(response.data);
			
			location.href="/";
		}).fail(function(error) {
			console.log(error);
		})
	},
	
	updatePost: function() {
		if(!confirm("수정하시겠습니까?"))
			return;
		
		const post = {
			id: $("#id").val(),
			title: $("#title").val(),
			content: $("#content").val()
		}
		console.log(post)
		$.ajax({
			type: "PUT",
			url: "/post",
			data: JSON.stringify(post),
			contentType: "application/json; charset=utf-8"
		}).done(function(response) {
			alert(response.data);
			
			location.href = "/post/" + post.id;
		}).fail(function(error) {
			console.log(error);
		})
	},
	
	deletePost : function() {
		if(!confirm("정말로 삭제하시겠습니까?"))
			return;
		
		const id = $("#id").text();
		
		$.ajax({
			type: "DELETE",
			url: `/post/${id}`
		}).done(function(response) {
			alert(response.data);
			
			location.href = "/";
		}).fail(function(error) {
			console.log(error);
		})
	}
};

postObject.init();










