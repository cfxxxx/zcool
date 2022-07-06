from wsgiref import headers
import requests
from lxml import etree
import os
import re
import json
if __name__ == '__main__':
    #url = 'https://www.zcool.com.cn/?p=1&action=zcool_index_old#tab_anchor'
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5060.53 Safari/537.36 Edg/103.0.1264.37'}

    #鍒涘缓涓�?涓�鏂囦欢澶�?
    if not os.path.exists('./picUrls'):
        os.mkdir('./picUrls')

    for i in range(1, 101):
        url = 'https://www.zcool.com.cn/?p=' + str(i) + '&action=zcool_index_old#tab_anchor'
        response = requests.get(url, headers=headers)
        #response.encoding = 'utf-8'
        page_text = response.text

        #鏁版嵁瑙ｆ瀽锛歴rc鐨勫睘鎬у�?硷紝alt灞炴�?у�?�?
        tree = etree.HTML(page_text)
        div_list = tree.xpath('//div[@class="sc-hKwDye jgyXZm workList"]/div[@class="sc-vkzd68-0 iGoLzH cardBox contentCardBox"]')

        

        for div in div_list:
            dict = {}
            img_src = div.xpath('./div[@class="cardImg"]/a/img/@src')[0]
            img_name = div.xpath('./div[@class="cardImg"]/a/img/@alt')[0]+'.jpg'
            img_name = re.sub(r'[:/\\?*鈥溾�?�?<>|]', '_', img_name)

            
            pic_theme = div.xpath('./section[@class="sc-jqn0up-2 oaRTx cardInfo"]/span[@class="sc-jqn0up-3 bUdQUi cardType"]/text()')[0]

            pic_view = div.xpath('./section[@class="sc-jqn0up-2 oaRTx cardInfo"]/div[@class="sc-hKwDye eESxKY cardIcons"]/div[1]//span/text()')[0]
            pic_common = div.xpath('./section[@class="sc-jqn0up-2 oaRTx cardInfo"]/div[@class="sc-hKwDye eESxKY cardIcons"]/div[2]//span/text()')[0]

            pic_good = div.xpath('./section[@class="sc-jqn0up-2 oaRTx cardInfo"]/div[@class="sc-hKwDye eESxKY cardIcons"]/div[3]//span/text()')[0]

            dict["img_name"] = img_name
            dict["theme"] = pic_theme
            dict["view"] = pic_view
            dict["common"] = pic_common
            dict["good"] = pic_good
            dict["img_src"] = img_src
            
            info_json = json.dumps(dict)
            info_json = info_json.encode('utf-8').decode()

            f = open('picUrls/infoUrl.txt','a',encoding='utf-8')
            f.write(info_json)
            #print(img_name, img_src)
            # 璇锋眰鍥剧�?�锛岃繘琛屾寔涔�?寲瀛樺�?
            #img_data = requests.get(url=img_src, headers=headers).content

            #img_path = 'picLibs/' + img_name
            #with open(img_path, 'wb') as fp:
            #    fp.write(img_data)
            #    print(img_name, '淇濆瓨鎴�?姛锛�?')
            
