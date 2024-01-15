import requests
from bs4 import BeautifulSoup
import json
from selenium import webdriver

if __name__=="__main__":
    url='https://www.bilibili.com/v/channel/1469519?tab=featured'
    headers={
        'Accept': 'application/json'
    }
    res=requests.get(url,headers=headers)
    html=res.text
    soup=BeautifulSoup(html,'html.parser')
    #title=soup.html.head.title.text[:-14]
    info1=soup.findAll('a',{'class':'up-name'})
    info2 = soup.findAll('span', {'class': 'up-name__text'})
    info3 = soup.findAll('span', {'class': 'count'})
    info4 = soup.findAll('span', {'class': 'like-text'})
    info5 = soup.findAll('a', {'class': 'video-name'})

    infos=[]
    for rez in range(len(info1)):
        result={
            '1':info1[rez].text,
            '2': info2[rez].text,
            '3': info3[rez].text,
            '4': info4[rez].text,
            '5': info5[rez].text
        }
        infos.append(result)
        print(result)
        print('\n')