    let currentTab = 0;
    const tabs = document.querySelectorAll('.tabs table');
    const scrollLeftBtn = document.querySelector('.scroll-left-btn');
    const scrollRightBtn = document.querySelector('.scroll-right-btn');

    function scrollTabs(direction) {
    currentTab += direction;
    if (currentTab < 0) currentTab = 0;
    if (currentTab >= tabs.length) currentTab = tabs.length - 1;
    updateTabVisibility();
}

    function updateTabVisibility() {
    tabs.forEach((tab, index) => {
        if (index === currentTab) {
            tab.style.display = 'table';
        } else {
            tab.style.display = 'none';
        }
    });
}

    updateTabVisibility();
