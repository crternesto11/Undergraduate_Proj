# -*- coding: utf-8 -*-
"""
Created on Sun Jun 26 16:37:42 2022

@author: 47086
"""

import requests
from selenium import webdriver
import time
import xlwt
from lxml import etree
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service

#全局设置爬行加速
chrome_options = Options()
# chrome_options = webdriver.ChromeOptions()
 
# ---------------------------优化选项---------------------------------- #
# 禁止图片
chrome_options.add_argument('blink-settings=imagesEnabled=false')
chrome_options.add_argument('--disable-images')
# 禁用JavaScript
chrome_options.add_argument("--disable-javascript")
chrome_options.add_argument("--disable-plugins")
chrome_options.add_argument('--disable-dev-shm-usage')
chrome_options.add_argument('--disable-software-rasterizer')
chrome_options.add_argument('--disable-extensions')
chrome_options.add_argument('--disable-gpu')
chrome_options.add_argument('--disable-java')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--mute-audio')
chrome_options.add_argument('--single-process')
# 屏蔽webdriver特征
chrome_options.add_argument("--disable-blink-features")
chrome_options.add_argument("--disable-blink-features=AutomationControlled")
chrome_options.add_argument('--incognito')
#不打开网页
chrome_options.add_argument("--headless")
chrome_options.add_argument("--disable-gpu")

#用于爬取视频集中每个视频的id
def get_url(offset):
    all_url=[]
    head = {
        'User-Agent': 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.53 Mobile Safari/537.36 Edg/103.0.1264.37'
    }
    js=requests.get("https://api.bilibili.com/x/web-interface/web/channel/featured/list?channel_id=1469519&filter_type=2020&offset=%s&page_size=30"%(offset),headers=head).json()
    offset=js["data"]["offset"]
    for i in js["data"]["list"]:
        urls="https://www.bilibili.com/video/"+i["bvid"]
        oids=i["id"]  #===》获取评论数据要用的
        dic={
                'url':urls,
                'oid':oids
            }
        all_url.append(dic)
    info=[]
    info.append(offset)
    info.append(all_url)
    return info

#获取评论
def get_comment(s):
    comments=[]
    head = {
        'User-Agent': 'Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.53 Mobile Safari/537.36 Edg/103.0.1264.37'
    }
    js=requests.get('https://api.bilibili.com/x/v2/reply/main?csrf=3a221207306ca50feb3d45872ae1828e&mode=3&next=1&oid=%s&plat=1&type=1'%(s),headers=head).json()
    for i in js["data"]["replies"]:
        comments.append(i["content"]["message"])
    return comments

#用于爬取每个视频的点赞投币等信息
def parser_data(url):
    dic = {}
    s = Service("E:\Anaconda3\chromedriver")
    bro = webdriver.Chrome(service=s)#,options=chrome_options)
    bro.get(url)
    bro.execute_script('window.scrollTo(0, document.body.scrollHeight)')  # 向下拉动一屏
    time.sleep(4)
    html=etree.HTML(bro.page_source)
    try:
        dic["title"]=html.xpath('//div[@id="viewbox_report"]/h1/@title')[0]
    except:
        dic["title"]=""
    try:
        dic["up"]=html.xpath('//div[@id="v_upinfo"]/div[2]/div[1]/a/text()')[0]
    except:
        dic["up"]=""
    try:
        dic["section2"]=html.xpath('//div[@id="v_tag"]/div[1]/ul[1]/*/a[@class="tag-link"]/text()')
    except:
        dic["section2"]=""
    try:
        dic["section"]=html.xpath('//div[@id="v_tag"]/div[1]/ul[1]/*/*/a[@class="tag-link"]/text()')
    except:
        dic["section"]=""
    try:
        dic["弹幕"]=html.xpath('//div[@id="viewbox_report"]/div[1]/span[2]/@title')[0]
    except:
        dic["弹幕"]=""
    try:
        dic["time"]=html.xpath('//div[@id="viewbox_report"]/div[1]/span[3]//text()')[0]
    except:
        dic["time"]=""
    try:
        dic["view"]=html.xpath('//div[@id="viewbox_report"]/div/span[@class="view item"]/@title')[0]
    except:
        dic["view"]=""
    try:
        dic["like"]=html.xpath('//div[@id="arc_toolbar_report"]/div[@class="toolbar-left"]/span[@class="like"]/@title')[0]
    except:
        dic["like"]=""
    try:
        dic["coin"]=html.xpath('//div[@id="arc_toolbar_report"]/div[@class="toolbar-left"]/span[@class="coin"]/@title')[0]
    except:
        dic["coin"]=""
    bro.quit()
    return dic



all_info=[]
info=get_url('')#起始页的offset
need_pages=20 #一页=30个视频
p=0
while p<need_pages:
    offset=info[0]#获取上一个包的offset，作为下一个包的offset参数的值
    for url in info[1]:
        video_info={}
        video_info=parser_data(url['url'])
        com=get_comment(url['oid'])
           #print(com)
        video_info['comments']=com
        all_info.append(video_info)
        print('我爬了')
    info=get_url(offset)
    p=p+1
    print('又爬了30个，好耶')
    print(p)
    print(offset)
    #写入Excel
book = xlwt.Workbook(encoding='utf-8',style_compression=0)
sheet = book.add_sheet('B站探店',cell_overwrite_ok=True)
col = ('标题','up主','分区1','分区2','弹幕数','投稿时间','播放量','点赞量','投币数','评论')
for i in range(0,10):
    sheet.write(0,i,col[i])
        
for i in range(len(all_info)):
    sheet.write(i+1,0,all_info[i]['title'])
    sheet.write(i+1,1,all_info[i]['up'])
    sheet.write(i+1,2,all_info[i]['section2'])
    sheet.write(i+1,3,all_info[i]['section'])
    sheet.write(i+1,4,all_info[i]['弹幕'])
    sheet.write(i+1,5,all_info[i]['time'])
    sheet.write(i+1,6,all_info[i]['view'])
    sheet.write(i+1,7,all_info[i]['like'])
    sheet.write(i+1,8,all_info[i]['coin'])
    for j in range(len(all_info[i]['comments'])):
        sheet.write(i+1,j+9,all_info[i]['comments'][j])
savepath = 'F:/B站探店视频数据2020.xls'
book.save(savepath)


    
    
