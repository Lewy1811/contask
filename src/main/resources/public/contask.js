function contentLoad() {
	var container = appendDivElement("container", document.body);
	var containerHeading = document.createElement("H1");
	containerHeading.innerText = "Wiadomości regionalne";
	container.appendChild(containerHeading);
	var containerPar = document.createElement("p");
	containerPar.innerText = "Wybierz kategorię, aby przejść do listy artykułów";
	container.appendChild(containerPar);
	appendCategorySelect(document.body);
}

function appendCategorySelect(targetElement) {
	var select = document.createElement("select");
	var categories = getRequestSync("http://localhost:8000/news/categories");
	for (let i = 0; i < categories.length; i++) {
		let option = document.createElement("option");
		option.value = categories[i];
		option.innerText = option.value;
		select.appendChild(option);
	}
	targetElement.appendChild(select);
	return select;
}

function newsLoad() {
	var restUrl = "http://localhost:8000/news/pl/technology";
	var response = getRequestSync(restUrl);
	var container = appendDivElement("container", document.body);
	var containerHeading = document.createElement("H1");
	containerHeading.innerText = "Wiadomości regionalne";
	container.appendChild(containerHeading);
	var containerPar = document.createElement("p");
	containerPar.innerText = "według kryteriów - kraj: " + response.country + ", kategoria: " + response.category;
	container.appendChild(containerPar);
	var articles = response.articles;
	articles.forEach(function(element) {
		var card = appendDivElement("card", document.body);
		card.style = "width: 35rem;";
		var image = document.createElement("img");
		if (element.imageUrl != null) {
			image.src = element.imageUrl;;
		} else {
			image.src = "https://www.azfoodandwine.com/wp-content/uploads/2018/03/no_image_available.jpeg"
		}
		image.className = "card-img-top";
		card.appendChild(image);
		var cardBody = createCardBody(element);
		card.appendChild(cardBody);
		document.body.appendChild(card);
	});
}

function getRequestSync(restUrl) {
	var xhr = new XMLHttpRequest();
	xhr.open("GET", restUrl, false);
	xhr.send();
	if (xhr.status === 200) {
		var jsonResp = JSON.parse(xhr.responseText);
	}
	return jsonResp;
}

function appendDivElement(className, targetElement) {
	var div = document.createElement("div");
	div.className = className;
	targetElement.appendChild(div);
	return div;
}

function createCardBody(article) {
	var cardBody = document.createElement("div");
	cardBody.className = "card-body";
	var cardTitle = document.createElement("H5");
	cardTitle.className = "card-title";
	cardTitle.innerText = article.title;
	cardBody.appendChild(cardTitle);
	var pDate = appendPToCardBody(article.date, cardBody);
	var pAuthor = appendPToCardBody(article.author, cardBody);
	var pSourceName = appendPToCardBody(article.sourceName, cardBody);
	var pDescription = appendPToCardBody(article.description, cardBody);
	var a = document.createElement("a");
	a.className = "btn btn-primary";
	a.innerText = "Otwórz";
	a.href = article.articleUrl;
	cardBody.appendChild(a);
	return cardBody;
}

function appendPToCardBody(innerText, targetElement) {
	var pElement = document.createElement("p");
	pElement.innerText = innerText;
	pElement.className = "card-text";
	targetElement.appendChild(pElement);
	return pElement;
}