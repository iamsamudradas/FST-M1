import pytest

def test_sum(sample_list):
    assert sum(sample_list) == 6

def test_writting_toFile(resource):
    resource.write("hello world")