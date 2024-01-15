# -*- coding: utf-8 -*-
"""
Created on Sun Jun 26 16:29:08 2022

@author: 47086
"""

from os import times
from bs4 import BeautifulSoup
import urllib.error
from selenium.webdriver.common.keys import Keys  # 模仿键盘,操作下拉框的
from bs4 import BeautifulSoup  # 解析html的
from selenium import webdriver
import time
from selenium.webdriver.chrome.options import Options


def main():
    pa()


def pa():  # 爬取动态下滑加载网页
    chrome_options = Options()
    # chrome_options.add_argument('--headless')
    chrome_options.add_argument("--start-maximized")

    driver = webdriver.Chrome("E:\Anaconda3\pkgs\chromedriver_win32\chromedriver.exe", chrome_options=chrome_options)


    driver.get("https://wenku.baidu.com/search?lm=0&od=0&ie=utf-8&word=csdn")
    time.sleep(3)
    js = "return action=document.body.scrollHeight"
    height = driver.execute_script(js)

    # 将滚动条调整至页面底部
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(5)

    # 定义初始时间戳（秒）
    t1 = int(time.time())

    # 定义循环标识，用于终止while循环
    status = True

    # 重试次数
    num = 0

    while status:
        # 获取当前时间戳（秒）
        t2 = int(time.time())
        # 判断时间初始时间戳和当前时间戳相差是否大于30秒，小于30秒则下拉滚动条
        if t2 - t1 < 30:
            new_height = driver.execute_script(js)
            if new_height > height:
                time.sleep(1)
                driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
                # 重置初始页面高度
                height = new_height
                # 重置初始时间戳，重新计时
                t1 = int(time.time())
        elif num < 3:  # 当超过30秒页面高度仍然没有更新时，进入重试逻辑，重试3次，每次等待30秒
            time.sleep(3)
            num = num + 1
        else:  # 超时并超过重试次数，程序结束跳出循环，并认为页面已经加载完毕！
            print("滚动条已经处于页面最下方！")
            status = False
            # 滚动条调整至页面顶部
            driver.execute_script('window.scrollTo(0, 0)')
            break

    # 打印页面源码
    content = driver.page_source
    print(content)

if __name__ == "__main__":
    main()