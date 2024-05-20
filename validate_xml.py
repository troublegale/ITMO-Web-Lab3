import os
import sys
from lxml import etree

def validate_xml(directory):
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith('.xml'):
                xml_file = os.path.join(root, file)
                try:
                    etree.parse(xml_file)
                    print(f'Validated {xml_file}')
                except etree.XMLSyntaxError as e:
                    print(f'Error validating {xml_file}: {e}')
    print('Validation complete.')


if __name__ == "__main__":
    directory = sys.argv[1]
    validate_xml(directory)
