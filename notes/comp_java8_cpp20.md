# Comparison of Java 8+ and C++ 20 on Syntax

## Functional programming

Java lambda expressions are more concise than C++'s in that:

- argument types can be omitted;
- the `{}` and `return` keyword can be omitted.

MapReduce on arrays of primitive values in Java
isn't fun, because one often has to write extra code to bridge
the gap between primitive type and reference type. Notice that
`IntStream` is not a `Stream<T>`. Similarly, there is a `LongStream`,
and `DoubleStream`. As a result,

- `IntStream` doesn't have one map method, instead, it has 5:
  - `map`
  - `mapToDouble`
  - `mapToLong`
  - `mapToObj`
  - `boxed`

- And `Steram<T>` has these many:
  - `map`
  - `mapToDouble`
  - `mapToInt`
  - `mapToLong`
  - `flatMapToDouble`
  - `floatMapToInt`
  - `floatMapToLong`
  
- `Stream<T>` cannot be converted to an array of primitive directly,
  it has to be first converted to `IntStream` or something.
  
- One can never transform a `char[]` using the `Stream` API,
  because he can't go further than `Stream<Character>`.
  
C++ has no these issues. So far, I've found these related functions
and they apply everywhere:
- `std::transform()`
- `std::reduce()`
- `std::transform_reduce()`
- `std::accumulate()`

## C++ references are not objects; can't swap them

Say I'm writing an algorithm taking two vector references,
nums1 and nums2, as input. I want to swap nums1 and nums2
if nums1 is longer.

This swapping is nothing special in Java, however in C++,
I can only think of these ways:

- introduce a helper function and call it in a if-condition,
  passing ot it either (nums1, nums2) or (nums2, nums1).
- use `std::ref()` to generate reference wrappers, which are objects.
- use pointers.

## Find out minimum and/or maximum element of a collection

Java: `IntStream.max()`, `IntStream.min()`.

C++: `std::min_element()`, `std::max_element()`, `std::minmax_element()`.

## A function to swap two primitive values

Java: impossible.

C++: `std::swap()`

## Binary search

Java: `Arrays.binarySearch()` or `Collections.binarySearch()`

C++: `std::binary_search`, `std::lower_bound()`
and `std::upper_bound()`.

If the target value has duplicates, Java's API can't tell you
where are the first and last occurences.

## It's impossible to write generic algorithms for arrays of primitives in Java

Say, you decide to write a `swap(int[] arr, int i, int j)` function.
Chances are that you may also need to write a one for `char[]`.

## C++ has `auto`, and Java 10 has `var`

## Curly brace initialization

Java: Only when defining a variable, can't use them to initialize
function arguments.

C++: no restrictions.

## Destructuring assignment

Java: NA.

C++: Restricted. The feature is called
[Structured binding declaration](https://en.cppreference.com/w/cpp/language/structured_binding).
Only works if the structure is known at compile time, such as
arrays and tuple-likes. Unfortunately, `vector` is not such a structure.

## Output params

Java: Wrap primitive values with objects. Such as `AtomicInteger`
or `int[]` for an `int` value.

C++: Simply pass by reference.

