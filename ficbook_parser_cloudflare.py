import cloudscraper
import sys


def main():
    scraper = cloudscraper.create_scraper()
    f = open(sys.argv[1], "a", encoding='utf-8')
    f.write(scraper.get(sys.argv[2]).text)
    f.close()


if __name__ == "__main__":
    main()

