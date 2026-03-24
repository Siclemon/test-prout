(async function () {
    const element = document.body;
    const html = await fetchHTML();
    element.insertAdjacentHTML("afterbegin", html);
})();