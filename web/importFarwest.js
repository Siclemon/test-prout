(async function () {
    const element = document.body;
    const html = await fetchHTML('farwest.html');
    element.insertAdjacentHTML('beforeend', html);
})();