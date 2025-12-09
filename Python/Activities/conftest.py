import pytest

@pytest.fixture
def sample_list():
    return[1,2,3]

@pytest.fixture
def resource():
    conn = open("file.txt", "w")
    yield conn
    conn.close()

# Create fixture
@pytest.fixture
def num_list():

    # Create list
    int_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
    
    # Alternatively
    # int_list = list(range(11))

    return int_list

# Write test method
def test_sum(num_list):

    # Initialize sum
    sum = 0

    # Add number in the list
    for n in num_list:
        sum += n

    # Assertion
    assert sum == 55