import cloudscraper


def main():
    f = open('C:/Users/savin/Desktop/text.txt', 'r', encoding='utf-8')
    scraper = cloudscraper.create_scraper()
    lines = f.readlines()
    f.close()
    for line in lines:
      #  scraper.get(line).text.g
        f = open("C:/Users/savin/Desktop/ficbook_text.txt", "a", encoding='utf-8')
        f.write(scraper.get(line).text)
    f.close()


if __name__ == "__main__":
    main()
