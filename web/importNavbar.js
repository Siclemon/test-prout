(async function () {
    const element = document.body;
    const html = await fetchHTML('nav.html');
    element.insertAdjacentHTML("afterbegin", html);
})();