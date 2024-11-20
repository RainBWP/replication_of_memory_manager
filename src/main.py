import argparse
def get_args():
        parser = argparse.ArgumentParser(description="Replication of Memory Manager")
        # Add arguments here
        parser.add_argument("-f", "--file", help="Archivo que lee", required=True)
        return parser.parse_args()

def convert_file(file: str) -> list:
    file = []
    with open(file, 'r') as f:
        lines = f.readlines()
        for line in lines:
            print(line)
    return lines


def main():
    pass

if __name__ == "__main__":
    args = get_args()
    print(args.file)
    file_data = convert_file(args.file)
    main()