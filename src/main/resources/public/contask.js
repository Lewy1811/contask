function contentLoad() {
	window.newsLoaded = false;
	window.pageSize = 10;
	var container = appendDivElement("container", document.body);
	var containerHeading = document.createElement("H1");
	containerHeading.innerText = "Contask - najciekawsze wiadomości w jednym miejscu";
	container.appendChild(containerHeading);
	container.setAttribute("id", "init-container");
	var containerPar = document.createElement("p");
	containerPar.innerText = "Wybierz kategorię, aby przejść do listy artykułów";
	container.appendChild(containerPar);
	var catSelect = appendSelect("categories", document.body);
	var bttnSelect = createButton(document.body, "Szukaj");
	bttnSelect.addEventListener("click", function() {
		clearSearchResults();
		newsLoadByCategory(catSelect.value);
		window.newsLoaded = true;
		divideToPages(window.apiResponse);
		loadPage(1);
	});
	var secContainer = appendDivElement("container", document.body);
	var containerPar = document.createElement("p");
	containerPar.innerText = "lub znajdź artykuły podając szukaną frazę";
	secContainer.appendChild(containerPar);
	var phraseInput = appendTxtInput(document.body);
	var bttnInput = createButton(document.body, "Szukaj");
	bttnInput.addEventListener("click", function() {
		clearSearchResults();
		if (phraseInput.value != "") {
			newsLoadByPhrase(phraseInput.value);
			if (window.apiResponse.articles.length > 0) {
				window.newsLoaded = true;
				divideToPages(window.apiResponse);
				loadPage(1);
			} else {
				alert("Brak wyników dla poszukiwanej frazy");
				window.newsLoaded = false;
			}
		} else {
			alert("Podaj szukaną frazę");
		}
	});
}

function clearSearchResults() {
	if (window.newsLoaded) {
		document.body.removeChild(document.getElementById("res-container"));
		for (let iChild = document.body.children.length - 1; iChild > 3; iChild--) {
			if (document.body.children[iChild].className == "card") {
				document.body.removeChild(document.body.children[iChild]);
			}
		}
	}
}

function appendSelect(value, targetElement) {
	var select = document.createElement("select");
	select.className = "select";
	var categories = getRequestSync("http://localhost:8000/news/" + value);
	for (let i = 0; i < categories.length; i++) {
		let option = document.createElement("option");
		option.value = categories[i];
		option.innerText = option.value;
		select.appendChild(option);
	}
	targetElement.appendChild(select);
	return select;
}

function createButton(targetObject, txtNodeValue) {
	var bttn = document.createElement("BUTTON");
	var bttnTxt = document.createTextNode(txtNodeValue);
	bttn.appendChild(bttnTxt);
	targetObject.appendChild(bttn);
	return bttn;
}

function appendTxtInput(targetElement) {
	var input = document.createElement("INPUT");
	input.className = "input";
	input.type = "text";
	input.id = "input_01";
	targetElement.appendChild(input);
	return input;
}

function newsLoadByCategory(category) {
	var restUrl = "http://localhost:8000/news/pl/" + category;
	var response = getRequestSync(restUrl);
	window.apiResponse = response;
	window.responseInfoString = "według kryteriów - kraj: " + response.country + ", kategoria: " + response.category;
}

function newsLoadByPhrase(phrase) {
	var restUrl = "http://localhost:8000/news/find?phrase=" + phrase;
	var response = getRequestSync(restUrl);
	window.apiResponse = response;
	window.responseInfoString = "dla szukanej frazy: " + phrase;
}

function loadPage(pageNum) {
	window.actualPageNumber = pageNum;
	loadCards(window.pages[pageNum - 1]);
	appendPagingButtons();
}

function appendPagingButtons() {
	if (window.pages.length > 1) {
		var prevBttn = createButton(document.getElementById("res-container"), "<");
		prevBttn.setAttribute("id", "prev-bttn");
		prevBttn.className = "pag-bttn";
		prevBttn.addEventListener("click", function() {
			clearSearchResults();
			loadPage(window.actualPageNumber - 1);
		});
		if (window.actualPageNumber == 1) {
			prevBttn.disabled = true;
		} else {
			prevBttn.disabled = false;
		}
		var pagingPar = appendP(window.actualPageNumber + " / " + window.pages.length, document.getElementById("res-container"));
		var nextBttn = createButton(document.getElementById("res-container"), ">");
		nextBttn.setAttribute("id", "next-bttn");
		nextBttn.className = "pag-bttn";
		nextBttn.addEventListener("click", function() {
			clearSearchResults();
			loadPage(window.actualPageNumber + 1);
		});
		if (window.actualPageNumber == window.pages.length) {
			nextBttn.disabled = true;
		} else {
			nextBttn.disabled = false;
		}
	}
}

function divideToPages(response) {
	window.pages = [];
	for (let iPage = 0; iPage < countNumberOfPages(window.apiResponse.articles.length, window.pageSize); iPage++) {
		let page = window.apiResponse.articles.slice(iPage * window.pageSize, (iPage + 1) * window.pageSize);
		window.pages.push(page);
	}
}
		
function loadCards(responseArray) {
	var container = appendDivElement("container", document.body);
	var containerHeading = document.createElement("H1");
	containerHeading.innerText = "Znalezione artykuły";
	container.appendChild(containerHeading);
	container.setAttribute("id", "res-container");
	var containerPar = document.createElement("p");
	containerPar.innerText = window.responseInfoString;
	container.appendChild(containerPar);
	var articles = responseArray;
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
		var imgDiv = appendDivElement("img-container", card);
		imgDiv.appendChild(image);
		card.appendChild(imgDiv);
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
	var pDate = appendP(article.date, cardBody);
	if (article.author == null) {article.author = "-";}
	var pAuthor = appendP("autor:   " + article.author, cardBody);
	var pSourceName = appendP("źródło:   " + article.sourceName, cardBody);
	var pDescription = appendP(article.description, cardBody);
	var a = document.createElement("a");
	a.className = "btn btn-primary";
	a.innerText = "Otwórz";
	a.href = article.articleUrl;
	cardBody.appendChild(a);
	return cardBody;
}

function appendP(innerText, targetElement) {
	var pElement = document.createElement("p");
	pElement.innerText = innerText;
	pElement.className = "p-text";
	targetElement.appendChild(pElement);
	return pElement;
}

function countNumberOfPages(responseSize, pageSize) {
	return Math.ceil(responseSize / pageSize);
	//var pagesNum = responseSize / pageSize;
	//pageNum = pageNum.toExponential(0);
	//if (responseSize % pageSize > 0) {
		//pagesNum = pagesNum + 1;
	//}
	//return pagesNum;
}